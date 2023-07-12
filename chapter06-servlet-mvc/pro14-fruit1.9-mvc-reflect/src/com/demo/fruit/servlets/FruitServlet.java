package com.demo.fruit.servlets;



import com.demo.fruit.bean.Fruit;
import com.demo.fruit.dao.impl.FruitDAOImpl;
import com.demo.myspringmvc.ViewBaseServlet;
import com.demo.util.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;


/**
 * ClassName: IndexServlet
 * Description:
 *
 * @Author wggglggg
 * @Create 2023/7/9 15:38
 * @Version 1.0
 */
@WebServlet("/fruit.do")
public class FruitServlet extends ViewBaseServlet {
    FruitDAOImpl fruitDAO = new FruitDAOImpl();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        String operate = request.getParameter("operate");
        if (StringUtil.isEmpty(operate)){
            operate = "index";
        }

        // 获取 当前类中所有方法
        Method[] methods = getClass().getDeclaredMethods();

        for (Method method : methods){
            // 获取 方法名称
            String methodName = method.getName();
            if (operate.equals(methodName)){
                try {
                    method.invoke(this, request, response);
//                    method(request,response);
                    return;
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                } catch (InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        throw new RuntimeException("operate值 非法");



//        switch (operate){
//            case "index":
//                showIndex(request, response);
//                break;
//            case "edit":
//                edit(request, response);
//                break;
//            case "update":
//                update(request, response);
//                break;
//            case "add":
//                add(request, response);
//                break;
//            case "showAdd":
//                showAdd(request,response);
//            case "del":
//                del(request,response);
//                break;
//            default:
//
//
//        }
    }


    private void showIndex(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        index(request,response);
    }

    private void showAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processTemplate("add", request, response);
    }

    private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        String fname = request.getParameter("fname");
        int price = Integer.parseInt(request.getParameter("price"));
        int fcount = Integer.parseInt(request.getParameter("fcount"));
        String remark = request.getParameter("remark");
        HttpSession session = request.getSession();
        Object pageNo = session.getAttribute("pageNo");

        fruitDAO.addFruit(new Fruit(0, fname, price, fcount, remark));

        response.sendRedirect("fruit.do?pageNo="+pageNo);
//        processTemplate("add", request, response);
    }

    private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

            response.sendRedirect("fruit.do");
        }
    }

    private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        FruitDAOImpl fruitDAO = new FruitDAOImpl();

        String fidStr = request.getParameter("fid");
        if (StringUtil.isNotEmpty(fidStr)){
            int fid = Integer.parseInt(fidStr);
            Fruit fruit = fruitDAO.getFruitById(fid);

            request.setAttribute("fruit", fruit);
            processTemplate("edit", request, response);
        }
    }

    private void index(HttpServletRequest request, HttpServletResponse response) throws IOException {
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

    private void del(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        String fidStr = request.getParameter("fid");
        if (StringUtil.isNotEmpty(fidStr)){
            int fid = Integer.parseInt(fidStr);
            fruitDAO.deleteFruit(fid);
            Object pageNo = session.getAttribute("pageNo");

            response.sendRedirect("fruit.do?pageNo=" + pageNo);
        }
    }

}
