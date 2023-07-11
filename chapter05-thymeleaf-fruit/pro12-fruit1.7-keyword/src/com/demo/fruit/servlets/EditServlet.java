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
 * ClassName: EditServlet
 * Description:
 *
 * @Author wggglggg
 * @Create 2023/7/10 9:29
 * @Version 1.0
 */

@WebServlet("/edit.do")
public class EditServlet extends ViewBaseServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        FruitDAOImpl fruitDAO = new FruitDAOImpl();

        String fidStr = request.getParameter("fid");
        if (StringUtil.isNotEmpty(fidStr)){
            int fid = Integer.parseInt(fidStr);
            Fruit fruit = fruitDAO.getFruitById(fid);

            request.setAttribute("fruit", fruit);
            processTemplate("edit", request, response);
        }
    }
}
