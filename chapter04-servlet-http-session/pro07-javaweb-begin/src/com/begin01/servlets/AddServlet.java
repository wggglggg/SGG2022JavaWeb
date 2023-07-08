package com.begin01.servlets;

import com.begin01.fruit.bean.Fruit;
import com.begin01.fruit.dao.impl.FruitDAOImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * ClassName: AddServlet
 * Description:
 *
 * @Author wggglggg
 * @Create 2023/7/8 18:42
 * @Version 1.0
 */
public class AddServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String fname = request.getParameter("fname");
        String priceStr = request.getParameter("price");
        int price = Integer.parseInt(priceStr);
        String fcountStr = request.getParameter("fcount");
        int fcount = Integer.parseInt(fcountStr);
        String remark = request.getParameter("remark");

        FruitDAOImpl fruitDAO = new FruitDAOImpl();
        boolean flag = fruitDAO.addFruit(new Fruit(0, fname, price, fcount, remark));

        System.out.println(flag ? "添加成功" : "添加失败");
    }
}
