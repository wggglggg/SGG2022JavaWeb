package com.demo.fruit.servlets;

import com.demo.fruit.dao.impl.FruitDAOImpl;
import com.demo.myspringmvc.ViewBaseServlet;
import com.demo.util.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ClassName: DelServlet
 * Description:
 *
 * @Author wggglggg
 * @Create 2023/7/10 15:22
 * @Version 1.0
 */
@WebServlet("/del.do")
public class DelServlet extends ViewBaseServlet {
    FruitDAOImpl fruitDAO = new FruitDAOImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fidStr = request.getParameter("fid");
        if (StringUtil.isNotEmpty(fidStr)){
            int fid = Integer.parseInt(fidStr);
            fruitDAO.deleteFruit(fid);

            response.sendRedirect("index");
        }
    }
}
