package com.a.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * ClassName: Demo07Servlet
 * Description:
 *
 * @Author wggglggg
 * @Create 2023/7/8 20:30
 * @Version 1.0
 */
public class Demo07Servlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("demo07.....");
    }
}
