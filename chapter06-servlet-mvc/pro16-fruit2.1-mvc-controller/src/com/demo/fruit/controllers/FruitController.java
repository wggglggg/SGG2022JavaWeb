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



    private String showAdd() {
        return "add";
    }

    private String add(String fname, Integer price, Integer fcount, String remark,HttpServletRequest request) {

        HttpSession session = request.getSession();
        Object pageNo = session.getAttribute("pageNo");

        fruitDAO.addFruit(new Fruit(0, fname, price, fcount, remark));

//        response.sendRedirect("fruit.do?pageNo="+pageNo);
//        processTemplate("add", request, response);
        return "redirect:fruit.do?pageNo=" + pageNo;
    }

    private String update(Integer fid, String fname, Integer price, Integer fcount, String remark){

        fruitDAO.updateFruit(new Fruit(fid,fname,price,fcount,remark));
        //  response.sendRedirect("fruit.do");
        return "redirect:fruit.do";

    }

    private String edit(Integer fid, HttpServletRequest request) {

        Fruit fruit = fruitDAO.getFruitById(fid);

        request.setAttribute("fruit", fruit);
//            processTemplate("edit", request, response);
        return "edit";
    }

    private String index(String oper, String keyword, Integer pageNo, HttpServletRequest request) {
        if (pageNo == null){
            pageNo = 1;

        }
        HttpSession session = request.getSession();



        if (StringUtil.isNotEmpty(oper) && "search".equals(oper)){
            pageNo = 1;
            if (StringUtil.isEmpty(keyword)){
                keyword = "";
            }
            session.setAttribute("keyword", keyword);
        } else {

            Object keywordObj = session.getAttribute("keyword");
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

    private String del(Integer fid,HttpSession session) {
        if (fid != null){
            fruitDAO.deleteFruit(fid);
            Object pageNo = session.getAttribute("pageNo");


//            response.sendRedirect("fruit.do?pageNo=" + pageNo);
            return "redirect:fruit.do?pageNo=" + pageNo;
        }
        return "error";
    }

}
