package com.demo.fruit.controllers;

import com.demo.fruit.bean.Fruit;
import com.demo.fruit.dao.impl.FruitDAOImpl;
import com.demo.util.StringUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;


/**
 * ClassName: IndexServlet
 * Description:
 *
 * @Author wggglggg
 * @Create 2023/7/9 15:38
 * @Version 1.0
 */

public class FruitController {
    FruitDAOImpl fruitDAO = new FruitDAOImpl();



    private String showAdd(HttpServletRequest requeste) {
        return "add";
    }

    private String add(HttpServletRequest request) {

        String fname = request.getParameter("fname");
        int price = Integer.parseInt(request.getParameter("price"));
        int fcount = Integer.parseInt(request.getParameter("fcount"));
        String remark = request.getParameter("remark");
        HttpSession session = request.getSession();
        Object pageNo = session.getAttribute("pageNo");

        fruitDAO.addFruit(new Fruit(0, fname, price, fcount, remark));

//        response.sendRedirect("fruit.do?pageNo="+pageNo);
//        processTemplate("add", request, response);
        return "redirect:fruit.do";
    }

    private String update(HttpServletRequest request){



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

//            response.sendRedirect("fruit.do");
            return "redirect:fruit.do";
        }
        return "error";
    }

    private String edit(HttpServletRequest request) {


        String fidStr = request.getParameter("fid");
        if (StringUtil.isNotEmpty(fidStr)){
            int fid = Integer.parseInt(fidStr);
            Fruit fruit = fruitDAO.getFruitById(fid);

            request.setAttribute("fruit", fruit);
//            processTemplate("edit", request, response);
            return "edit";
        }
        return "error";
    }

    private String index(HttpServletRequest request) {

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

//        processTemplate("index", request, response);
        return "index";
    }

    private String del(HttpServletRequest request) {

        HttpSession session = request.getSession();
        String fidStr = request.getParameter("fid");
        if (StringUtil.isNotEmpty(fidStr)){
            int fid = Integer.parseInt(fidStr);
            fruitDAO.deleteFruit(fid);
            Object pageNo = session.getAttribute("pageNo");

//            response.sendRedirect("fruit.do?pageNo=" + pageNo);
            return "redirect:fruit.do";
        }
        return "error";
    }

}
