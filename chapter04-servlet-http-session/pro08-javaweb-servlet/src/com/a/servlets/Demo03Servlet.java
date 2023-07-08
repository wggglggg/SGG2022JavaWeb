package com.a.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * ClassName: Demo03Servlet
 * Description:
 *
 * @Author wggglggg
 * @Create 2023/7/8 20:19
 * @Version 1.0
 */
public class Demo03Servlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取session,如果获取不到，则创建一个新的
        HttpSession session = request.getSession();
        System.out.println("session = " + session.getId());
    }
}
