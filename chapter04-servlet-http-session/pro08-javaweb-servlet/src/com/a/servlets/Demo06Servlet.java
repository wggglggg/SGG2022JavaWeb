package com.a.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * ClassName: Demo06Servlet
 * Description:
 *
 * @Author wggglggg
 * @Create 2023/7/8 20:29
 * @Version 1.0
 */
public class Demo06Servlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("demo06......");
        //服务器端内部转发 客户端用户不知情，由服务端来自己完成
//        request.getRequestDispatcher("demo07").forward(request, response);
        //客户端重定向, 服务端发一个响应，让客户端再向demo07发出请求
        response.sendRedirect("demo07");
    }
}
