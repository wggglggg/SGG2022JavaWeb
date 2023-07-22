package com.javaweb.base;

import com.javaweb.exceps.DAOException;
import com.javaweb.util.ConnUtil;

import java.lang.reflect.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: BaseDAO
 * Description:
 *
 * @Author wggglggg
 * @Create 2023/7/21 14:22
 * @Version 1.0
 */


public abstract class BaseDAO<T> {


    protected Connection conn ;
    protected PreparedStatement psmt ;
    protected ResultSet rs ;

    //T的Class对象
    private Class entityClass ;

    public BaseDAO(){
        //getClass() 获取Class对象，当前我们执行的是new FruitDAOImpl() , 创建的是FruitDAOImpl的实例
        //那么子类构造方法内部首先会调用父类（BaseDAO）的无参构造方法
        //因此此处的getClass()会被执行，但是getClass获取的是FruitDAOImpl的Class
        //所以getGenericSuperclass()获取到的是BaseDAO的Class
        Type genericType = getClass().getGenericSuperclass();
        //ParameterizedType 参数化类型
        Type[] actualTypeArguments = ((ParameterizedType) genericType).getActualTypeArguments();
        //获取到的<T>中的T的真实的类型
        Type actualType = actualTypeArguments[0];
        try {
            entityClass = Class.forName(actualType.getTypeName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new DAOException("BaseDAO构造器 错误");
        }
    }

    protected Connection getConn(){
        conn = ConnUtil.getConn();
        return conn;
    }

//    protected void close(ResultSet rs , PreparedStatement psmt , Connection conn){
//          关闭功能移动到了ConnUtil内
//    }

    //给预处理命令对象设置参数
    private void setParams(PreparedStatement psmt , Object... params) throws SQLException {
        if(params!=null && params.length>0){
            for (int i = 0; i < params.length; i++) {
                psmt.setObject(i+1,params[i]);
            }
        }
    }



    //执行更新，返回影响行数
    protected int executeUpdate(String sql , Object... params){
        boolean insertFlag = false ;
        insertFlag = sql.trim().toUpperCase().startsWith("INSERT");
        try {
            conn = getConn();
            if(insertFlag){
                psmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            }else {
                psmt = conn.prepareStatement(sql);
            }
            setParams(psmt,params);
            int count = psmt.executeUpdate() ;

            if(insertFlag) {
                rs = psmt.getGeneratedKeys();
                if (rs.next()) {
                    return ((Long) rs.getLong(1)).intValue();
                }
            }
            return 0 ;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException("BaseDAO executeUpdate 错误");
        }
    }

    //通过反射技术给obj对象的property属性赋propertyValue值
    private void setValue(Object obj ,  String property , Object propertyValue) throws NoSuchMethodException, InvocationTargetException, InstantiationException, NoSuchFieldException, ClassNotFoundException, IllegalAccessException {
        Class clazz = obj.getClass();
        //获取property这个字符串对应的属性名 ， 比如 "fid"  去找 obj对象中的 fid 属性
        Field field = clazz.getDeclaredField(property);


        if(field!=null){

            //获取当前字段的类型名称
            String typeName = field.getType().getName();
            //判断如果是自定义类型， 则需要调用自定义类型构造方法（一个参数的构造方法）
            //假设typeName是“com.javaweb.qqzone.bean.UserBasic”
            if (isMyType(typeName)){
                Class typeNameClass = Class.forName(typeName);
                Constructor constructor = typeNameClass.getDeclaredConstructor(Integer.class);
                propertyValue = constructor.newInstance(propertyValue);

            }

//            if (propertyValue.getClass().toString().equals("class java.time.LocalDateTime")){
//                propertyValue = Timestamp.valueOf((LocalDateTime) propertyValue);
//            }

            field.setAccessible(true);
            field.set(obj,propertyValue);

        }
    }

    //执行复杂查询，返回例如统计结果
    protected Object[] executeComplexQuery(String sql , Object... params){
        try {
            conn = getConn() ;
            psmt = conn.prepareStatement(sql);
            setParams(psmt,params);
            rs = psmt.executeQuery();

            //通过rs可以获取结果集的元数据
            //元数据：描述结果集数据的数据 , 简单讲，就是这个结果集有哪些列，什么类型等等

            ResultSetMetaData rsmd = rs.getMetaData();
            //获取结果集的列数
            int columnCount = rsmd.getColumnCount();
            Object[] columnValueArr = new Object[columnCount];
            //6.解析rs
            if(rs.next()){
                for(int i = 0 ; i<columnCount;i++){
                    Object columnValue = rs.getObject(i+1);     //33    苹果      5
                    columnValueArr[i]=columnValue;
                }
                return columnValueArr ;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException("BaseDAO executeComplexQuery 错误");
        }
        return null ;
    }

    //执行查询，返回单个实体对象
    protected T load(String sql , Object... params){
        try {
            conn = getConn() ;
            psmt = conn.prepareStatement(sql);
            setParams(psmt,params);
            rs = psmt.executeQuery();

            //通过rs可以获取结果集的元数据
            //元数据：描述结果集数据的数据 , 简单讲，就是这个结果集有哪些列，什么类型等等

            ResultSetMetaData rsmd = rs.getMetaData();
            //获取结果集的列数
            int columnCount = rsmd.getColumnCount();
            //6.解析rs
            if(rs.next()){
                T entity = (T)entityClass.newInstance();

                for(int i = 0 ; i<columnCount;i++){
                    String columnName = rsmd.getColumnName(i+1);            //fid   fname   price
                    Object columnValue = rs.getObject(i+1);     //33    苹果      5
                    setValue(entity,columnName,columnValue);
                }
                return entity ;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("BaseDAO load错误");
        }
        return null ;
    }


    //执行查询，返回List
    protected List<T> executeQuery(String sql , Object... params){
        List<T> list = new ArrayList<>();
        try {
            conn = getConn() ;
            psmt = conn.prepareStatement(sql);
            setParams(psmt,params);
            rs = psmt.executeQuery();

            //通过rs可以获取结果集的元数据
            //元数据：描述结果集数据的数据 , 简单讲，就是这个结果集有哪些列，什么类型等等

            ResultSetMetaData rsmd = rs.getMetaData();
            //获取结果集的列数
            int columnCount = rsmd.getColumnCount();
            //6.解析rs
            while(rs.next()){
                T entity = (T)entityClass.getDeclaredConstructor().newInstance();

                for(int i = 0 ; i<columnCount;i++){
//                    String columnName = rsmd.getColumnName(i+1);            //fid   fname   price
                    String columnName = rsmd.getColumnLabel(i+1);            //fid   fname   price
                    Object columnValue = rs.getObject(i+1);     //33    苹果      5
                    setValue(entity,columnName,columnValue);
                }
                list.add(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("BaseDAO executeQuery错误");
        }
        return list ;
    }

    private static boolean isMyType(String typeName){
        return !isNotMyType(typeName);
    }
    private static boolean isNotMyType(String typeName){
        return "java.lang.Integer".equals(typeName)
                || "java.lang.String".equals(typeName)
                || "java.util.Date".equals(typeName)
                || "java.sql.Date".equals(typeName)
                || "java.lang.Double".equals(typeName)
                || "java.time.LocalDateTime".equals(typeName);
    }

//    private static boolean isDateTime(String typeName){
//        return "java.util.Date".equals(typeName) ||
//                "java.sql.Date".equals(typeName);
//    }
}
