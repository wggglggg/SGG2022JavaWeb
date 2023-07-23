package com.javaweb.trans;

import com.javaweb.util.ConnUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * ClassName: TransactionManager
 * Description:
 *
 * @Author wggglggg
 * @Create 2023/7/15 15:21
 * @Version 1.0
 */
public class TransactionManager {



    //开启事务
    public static void beginTrans() throws SQLException {
        ConnUtil.getConn().setAutoCommit(false);
    }

    //提交事务
    public static void commit() throws SQLException {
        Connection conn = ConnUtil.getConn();
        conn.commit();
        ConnUtil.closeConn();
    }

    //回滚事务
    public static void rollback() throws SQLException {
        Connection conn = ConnUtil.getConn();
        conn.rollback();
        ConnUtil.getConn().setAutoCommit(true);
        ConnUtil.closeConn();
    }
}
