package com.thymeleaf.fruit.servlets;

import com.thymeleaf.fruit.bean.Fruit;
import com.thymeleaf.fruit.dao.impl.FruitDAOImpl;
import com.thymeleaf.myssm.myspringmvc.ViewBaseServlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * ClassName: IndexServlet
 * Description:
 *
 * @Author wggglggg
 * @Create 2023/7/9 15:38
 * @Version 1.0
 */
@WebServlet("/index")
public class IndexServlet extends ViewBaseServlet {
    FruitDAOImpl fruitDAO = new FruitDAOImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {


        List<Fruit> fruitList = fruitDAO.getFruitList();

        HttpSession session = request.getSession();
        session.setAttribute("fruitList", fruitList);

        processTemplate("index", request, response);
    }

}
