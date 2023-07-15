package com.javaweb.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * ClassName: ConnUtil
 * Description:
 *
 * @Author wggglggg
 * @Create 2023/7/15 15:27
 * @Version 1.0
 */
public class ConnUtil {
    public static final String DRIVER = "com.mysql.cj.jdbc.Driver" ;
    public static final String URL = "jdbc:mysql:///fruitdb";
    public static final String USER = "root";
    public static final String PWD = "abc123" ;

    private static ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

    public static Connection createConn(){
        try {
            //1.加载驱动
            Class.forName(DRIVER);
            //2.通过驱动管理器获取连接对象
            return DriverManager.getConnection(URL, USER, PWD);
        } catch (ClassNotFoundException | SQLException e) {
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
