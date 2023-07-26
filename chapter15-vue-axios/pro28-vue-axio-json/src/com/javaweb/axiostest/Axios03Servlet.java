package com.javaweb.axiostest;

import com.google.gson.Gson;
import com.javaweb.pojo.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * ClassName: Axios03Servlet
 * Description:
 *
 * @Author wggglggg
 * @Create 2023/7/25 21:23
 * @Version 1.0
 */
@WebServlet("/axios03.do")
public class Axios03Servlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        BufferedReader reader = request.getReader();
        StringBuffer sb = new StringBuffer();

        String data;
        while ((data = reader.readLine()) != null){
            sb.append(data);
        }
        data = sb.toString();
        System.out.println("data = " + data);

        //已知 String
        //需要转化成 Java Object
        Gson gson = new Gson();

        //Gson有两个API
        //1.fromJson(string,T) 将字符串转化成java object
        //2.toJson(java Object) 将java object转化成json字符串，这样才能响应给客户端
        User user = gson.fromJson(data, User.class);
        System.out.println("user = " + user);
        user.setUname("caixingjuan");
        user.setPwd("ok11");

        String userJsonStr = gson.toJson(user);
        response.setCharacterEncoding("UTF-8");

        //MIME-TYPE
        response.setContentType("application/json;charset=UTF-8");
//        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().write(userJsonStr);
    }
}
