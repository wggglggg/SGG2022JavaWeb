package utils;




import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.*;

import java.util.Properties;


/**
 * ClassName: JDBCUtils
 * Description:
 * v2.0版本工具类
 *   内部包含一个连接池对象,并且对外提供获取连接和回收连接的方法!
 *
 * 小建议:
 *   工具类的方法,推荐写成静态,外部调用会更加方便!
 *
 * 实现:
 *   属性 连接池对象 [实例化一次]
 *       单例模式
 *       static{
 *           全局调用一次
 *       }
 *   方法
 *       对外提供连接的方法
 *       回收外部传入连接方法
 *
 *
 * TODO:
 *    利用线程本地变量,存储连接信息! 确保一个线程的多个方法可以获取同一个connection!
 *    优势: 事务操作的时候 service 和 dao 属于同一个线程,不同再传递参数了!
 *    大家都可以调用getConnection自动获取的是相同的连接池!
 *
 * @Author wggglggg
 * @Create 2023/7/1 10:00
 * @Version 1.0
 */
public class JdbcUtilsV2 {

    /**
     * ClassName: JDBCUtils
     * Description: 操作数据库的工具类
     *
     * @Author wggglggg
     * @Create 2023/6/29 9:05
     * @Version 1.0
     */

    public static Connection getConnection() throws Exception {


        // 1.读取配置文件中的4个基本信息
//        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("servlet.properties");
//
//        Properties properties = new Properties();
//        properties.load(is);
//
//        String user = properties.getProperty("username");
//        String password = properties.getProperty("password");
//        String url = properties.getProperty("url");
//        String driverClass = properties.getProperty("driverClassName");


        // 2.加载驱动
        Class.forName("com.mysql.cj.jdbc.Driver");
//        Class.forName(driverClass);

        // 3.获取连接
        Connection connection = DriverManager.getConnection("jdbc:mysql:///fruitdb", "root", "abc123");
//        Connection connection = DriverManager.getConnection(url, user, password);


        return connection;
    }

    public static void releaseConnection(Connection conn, Statement ps) {

        try {
            if (ps != null)
                ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (conn != null)
                conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void releaseConnection(Connection conn, Statement ps, ResultSet rs) {
        try {
            if (rs != null)
                rs.close();
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
            if (conn != null)
                conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}


