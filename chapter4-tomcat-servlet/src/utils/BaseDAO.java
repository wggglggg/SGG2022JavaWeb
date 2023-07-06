package utils;

import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/**
 * ClassName: BaseDAO
 * Description:
 *
 * @Author wggglggg
 * @Create 2023/7/2 12:27
 * @Version 1.0
 */
public class BaseDAO {


    public int  executeUpdate(String sql, Object...args){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int rows = 0;
        try {
            connection = JdbcUtilsV2.getConnection();

            preparedStatement = connection.prepareStatement(sql);

            for (int i = 0; i < args.length; i++) {

                preparedStatement.setObject(i+1, args[i]);
            }

            rows = preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {

            JdbcUtilsV2.releaseConnection(connection, preparedStatement);
        }

        return rows;
    }

    public <T> List<T> executeQuery(Class<T> clazz, String sql, Object...args) throws Exception {

        Connection connection = JdbcUtilsV2.getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        if (args != null && args.length != 0){
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i+1, args[i]);
            }
        }

        ResultSet rs = preparedStatement.executeQuery();

        ResultSetMetaData rsmd = rs.getMetaData();
        int columnCount = rsmd.getColumnCount();

        List<T> list = new ArrayList<>();

        while (rs.next()){

            T t = clazz.getDeclaredConstructor().newInstance();

            for (int i = 0; i < columnCount; i++) {
                Object columnValue = rs.getObject(i + 1);
                String columnLabel = rsmd.getColumnLabel(i + 1);

                Field field = clazz.getDeclaredField(columnLabel);
                field.setAccessible(true);
                field.set(t, columnValue);
            }
            list.add(t);

        }



        if (connection.getAutoCommit()){

            JdbcUtilsV2.releaseConnection(connection, preparedStatement, rs);
        }

        return list;
    }
}
