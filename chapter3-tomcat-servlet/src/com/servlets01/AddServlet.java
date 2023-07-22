package com.servlets01;

import com.servlets01.dao.impl.FruitDAOImpl;
import com.servlets01.pojo.Fruit;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

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
        FruitDAOImpl fruitDAO = new FruitDAOImpl();
        boolean flag = fruitDAO.addFruit(new Fruit(100, fname, price, fcount, remark));

        System.out.println(flag ? "添加成功" : "添加失败");

    }
}
