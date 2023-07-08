package com.a.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * ClassName: Demo05Servlet
 * Description:
 *
 * @Author wggglggg
 * @Create 2023/7/8 20:23
 * @Version 1.0
 */
public class Demo05Servlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Object user = request.getSession().getAttribute("user");
        System.out.println(user);
    }
}
