package com.demo;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ClassName: Demo05Servlet
 * Description:
 *
 * @Author wggglggg
 * @Create 2023/7/9 21:39
 * @Version 1.0
 */
@WebServlet("/demo05")
public class Demo05Servlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.向application保存作用域保存数据
        //ServletContext : Servlet上下文
        ServletContext servletContext = request.getServletContext();
        servletContext.setAttribute("uname", "liwen-COCO");
        //2.客户端重定向
        response.sendRedirect("demo06");

        //3.服务器端转发
//        request.getRequestDispatcher("demo06").forward(request,response);
    }
}
