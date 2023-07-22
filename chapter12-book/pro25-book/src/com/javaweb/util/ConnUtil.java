package com.javaweb.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * ClassName: ConnUtil
 * Description:
 *
 * @Author wggglggg
 * @Create 2023/7/15 15:27
 * @Version 1.0
 */
public class ConnUtil {

    private static ThreadLocal<Connection> threadLocal = new ThreadLocal<>();
    private static DataSource dataSource;

    static {
        try {
            Properties properties = new Properties();
            InputStream is = ConnUtil.class.getClassLoader().getResourceAsStream("druid.properties");
            properties.load(is);
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public static Connection createConn(){
        try {
//            Properties properties = new Properties();
//            InputStream is = ConnUtil.class.getClassLoader.getResourceAsStream("druid.properties");
//            properties.load(is);
//            dataSource = DruidDataSourceFactory.createDataSource(properties);
            //1.加载驱动
            //2.通过驱动管理器获取连接对象
            return dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static Connection getConn(){
//        Connection connection = threadLocal.get();
//        if (connection != null){
//            return connection;
//        }
//        connection = createConn();
//        threadLocal.set(connection);
//        return connection;

        Connection conn = threadLocal.get();
        if(conn==null){
            conn =createConn();
            threadLocal.set(conn);
        }
        return threadLocal.get() ;
    }


    public static void closeConn() throws SQLException {
        Connection connection = threadLocal.get();
        if (connection == null){
            return;
        }
        if (!connection.isClosed()) {
            connection.close();
            threadLocal.set(null);
        }
    }
}
