package com.begin01.util;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * ClassName: JDBCUtils
 * Description: 操作数据库的工具类
 *
 * @Author wggglggg
 * @Create 2023/7/7 7:36
 * @Version 1.0
 */
public class JDBCUtils {

    /**
     *
     * @Description 获取数据库的连接
     * @author shkstart
     * @date 上午9:11:23
     * @return
     * @throws Exception
     */
    public static Connection getConnection() throws Exception {
        // 1.读取配置文件中的4个基本信息
        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("jdbc.properties");

        Properties properties = new Properties();
        properties.load(is);

        String user = properties.getProperty("username");
        String password = properties.getProperty("password");
        String url = properties.getProperty("url");
        String driverClassName = properties.getProperty("driverClassName");

        // 2.加载驱动
        Class.forName(driverClassName);

        // 3.获取连接
        Connection connection = DriverManager.getConnection(url, user, password);

        return connection;
    }

    /**
     *
     * @Description 关闭连接和Statement的操作
     * @author shkstart
     * @date 上午9:12:40
     * @param connection
     * @param ps
     */
    public static void closeResource(Connection connection, PreparedStatement ps){

        try {
            if (connection != null)
                connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (ps != null)
                ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @Description 关闭资源操作
     * @author shkstart
     * @date 上午10:21:15
     * @param connection
     * @param ps
     * @param rs
     */
    public static void closeResource(Connection connection, PreparedStatement ps, ResultSet rs){
        try {
            if (connection != null)
                connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (ps != null)
                ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (rs != null)
                rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
