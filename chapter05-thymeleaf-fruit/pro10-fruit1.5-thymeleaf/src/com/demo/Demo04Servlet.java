package com.demo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ClassName: Demo04Servlet
 * Description:
 *
 * @Author wggglggg
 * @Create 2023/7/9 21:25
 * @Version 1.0
 */
@WebServlet("/demo04")
public class Demo04Servlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Object uname = request.getSession().getAttribute("uname");
        System.out.println("name = " + uname);
    }
}
