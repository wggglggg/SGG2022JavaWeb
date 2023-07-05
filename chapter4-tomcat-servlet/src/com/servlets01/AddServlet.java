package com.servlets01;



import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.Test;
import utils.BaseDAO;

import java.io.IOException;
import java.sql.SQLException;

/**
 * ClassName: AddServlet
 * Description:
 *
 * @Author wggglggg
 * @Create 2023/7/5 15:49
 * @Version 1.0
 */
public class AddServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fname = request.getParameter("fname");
        String priceStr = request.getParameter("price");
        int price = Integer.parseInt(priceStr);
        String fcountStr = request.getParameter("fcount");
        int fcount = Integer.parseInt(fcountStr);
        String remark = request.getParameter("remark");

//        System.out.println("fname = " + fname);
//        System.out.println("price = " + price);
//        System.out.println("fcount = " + fcount);
//        System.out.println("remark = " + remark);
        BaseDAO baseDAO = new BaseDAO();
        int insertCount = 0;
        try {
            String sql = "insert into t_fruit(fname, price, fcount, remark) values(?,?,?,?)";
            insertCount =baseDAO.executeUpdate(sql, fname, price, fcount, remark);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        System.out.println(insertCount > 0 ? "添加成功" : "添加失败");

    }

    @Test
    public void testDo() throws SQLException {
        BaseDAO baseDAO = new BaseDAO();
        String sql = "insert into t_fruit(fname, price, fcount, remark) values(?,?,?,?)";
        int  insertCount =baseDAO.executeUpdate(sql, "葡萄", 3, 12, "黑葡萄");
    }
}
