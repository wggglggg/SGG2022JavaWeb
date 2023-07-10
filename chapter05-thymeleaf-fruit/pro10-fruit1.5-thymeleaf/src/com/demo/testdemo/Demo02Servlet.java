package com.demo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ClassName: Demo02Servlet
 * Description:
 *
 * @Author wggglggg
 * @Create 2023/7/9 21:11
 * @Version 1.0
 */
@WebServlet("/demo02")
public class Demo02Servlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Object uname = request.getAttribute("uname");
        System.out.println("uname = " + uname);
    }
}
