package com.javaweb.axiostest;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.javaweb.pojo.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * ClassName: Axios01Servlet
 * Description:
 *
 * @Author wggglggg
 * @Create 2023/7/25 16:17
 * @Version 1.0
 */

@WebServlet("/axios01.do")
public class Axios01Servlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
//        String uname = request.getParameter("uname");
//
//        String pwd = request.getParameter("pwd");
//        System.out.println("uname = " + uname);
//        System.out.println("pwd = " + pwd);



        BufferedReader reader = null;
        try {
            StringBuffer sb = new StringBuffer();
            reader = request.getReader();

            String data;
            while ((data = reader.readLine()) != null){
                sb.append(data);
            }

            data = sb.toString();

            //已知 String
            //需要转化成 Java Object
            Gson gson = new Gson();
            //Gson有两个API
            //1.fromJson(string,T) 将字符串转化成java object
            //2.toJson(java Object) 将java object转化成json字符串，这样才能响应给客户端
            User user = gson.fromJson(data, User.class);
            System.out.println(user);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (reader != null)
                reader.close();
        }

    }
}
