package com.javaweb.fruit.controllers;

import com.javaweb.fruit.bean.Fruit;
import com.javaweb.fruit.dao.impl.FruitDAOImpl;
import com.javaweb.fruit.service.FruitService;
import com.javaweb.fruit.service.impl.FruitServiceImpl;
import com.javaweb.util.StringUtil;

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
    FruitService fruitService = new FruitServiceImpl();



    private String showAdd() {
        return "add";
    }

    private String add(String fname, Integer price, Integer fcount, String remark,HttpServletRequest request) {

        HttpSession session = request.getSession();
        Object pageNo = session.getAttribute("pageNo");

        fruitService.addFruit(new Fruit(0, fname, price, fcount, remark));

//        response.sendRedirect("fruit.do?pageNo="+pageNo);
//        processTemplate("add", request, response);
        return "redirect:fruit.do?pageNo=" + pageNo;
    }

    private String update(Integer fid, String fname, Integer price, Integer fcount, String remark){

        fruitService.updateFruit(new Fruit(fid,fname,price,fcount,remark));
        //  response.sendRedirect("fruit.do");
        return "redirect:fruit.do";

    }

    private String edit(Integer fid, HttpServletRequest request) {

        Fruit fruit = fruitService.getFruitById(fid);

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



        session.setAttribute("pageNo", pageNo);
        List<Fruit> fruitList = fruitService.getFruitList(pageNo, keyword);
        session.setAttribute("fruitList", fruitList);


        //总记录条数
        Long count = fruitService.getFruitCount();
        //总页数
        Long pageCount = (count + 5 - 1) / 5;
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
            fruitService.deleteFruit(fid);
            Object pageNo = session.getAttribute("pageNo");


//            response.sendRedirect("fruit.do?pageNo=" + pageNo);
            return "redirect:fruit.do?pageNo=" + pageNo;
        }
        return "error";
    }

}
