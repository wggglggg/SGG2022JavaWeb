package com.demo.fruit.servlets;

import com.demo.fruit.bean.Fruit;
import com.demo.fruit.dao.impl.FruitDAOImpl;
import com.demo.myspringmvc.ViewBaseServlet;
import com.demo.util.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ClassName: UpdateServlet
 * Description:
 *
 * @Author wggglggg
 * @Create 2023/7/10 10:21
 * @Version 1.0
 */
@WebServlet("/update.do")
public class UpdateServlet extends ViewBaseServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        FruitDAOImpl fruitDAO = new FruitDAOImpl();
        request.setCharacterEncoding("utf-8");

        String fidStr = request.getParameter("fid");

        if (StringUtil.isNotEmpty(fidStr)){
            int fid = Integer.parseInt(fidStr);
            String fname = request.getParameter("fname");
            String priceStr = request.getParameter("price");
            int price = Integer.parseInt(priceStr);
            String fcountStr = request.getParameter("fcount");
            int fcount = Integer.parseInt(fcountStr);
            String remark = request.getParameter("remark");

            fruitDAO.updateFruit(new Fruit(fid,fname,price,fcount,remark));

            response.sendRedirect("index");
        }
    }
}
