package com.demo.fruit.servlets;



import com.demo.fruit.bean.Fruit;
import com.demo.fruit.dao.impl.FruitDAOImpl;
import com.demo.myspringmvc.ViewBaseServlet;
import com.demo.util.StringUtil;
import org.thymeleaf.inline.IInliner;

import javax.servlet.ServletException;
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        String keyword = null;
        int pageNo = 1;
        HttpSession session = request.getSession();

        String oper = request.getParameter("oper");

        if (StringUtil.isNotEmpty(oper) && "search".equals(oper)){
            keyword = request.getParameter("keyword");
            pageNo = 1;
            if (StringUtil.isEmpty(keyword)){
                keyword = "";
            }
            session.setAttribute("keyword", keyword);
        } else {

            String pageNoStr = request.getParameter("pageNo");
            if (StringUtil.isNotEmpty(pageNoStr)){
                pageNo = Integer.parseInt(pageNoStr);
            }
            Object keywordObj = request.getAttribute("keyword");
            if (keywordObj!= null){
                keyword = (String) keywordObj;
            } else {
                keyword = "";
            }

        }

        List<Fruit> fruitList = fruitDAO.getFruitList(pageNo, keyword);


        session.setAttribute("pageNo", pageNo);
        session.setAttribute("fruitList", fruitList);


        //总记录条数
        Long fruitCount = fruitDAO.getFruitCount();
        //总页数
        Long pageCount = (fruitCount + 5 - 1) / 5;
        /*
        总记录条数       总页数
        1               1
        5               1
        6               2
        10              2
        11              3
        fruitCount      (fruitCount+5-1)/5
         */
        session.setAttribute("pageCount", pageCount);

        processTemplate("index", request, response);
    }

}
