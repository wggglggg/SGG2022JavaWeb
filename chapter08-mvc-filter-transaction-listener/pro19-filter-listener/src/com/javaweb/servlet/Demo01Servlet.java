package com.javaweb.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ClassName: Demo01Servlet
 * Description:
 *
 * @Author wggglggg
 * @Create 2023/7/15 9:21
 * @Version 1.0
 */
@WebServlet("/demo01.do")
public class Demo01Servlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println(" demo01Servlet start ");
        request.getRequestDispatcher("demo01.html").forward(request, response);
    }
}
