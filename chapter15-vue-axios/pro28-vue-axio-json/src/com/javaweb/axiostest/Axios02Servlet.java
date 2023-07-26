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
 * ClassName: Axios02Servlet
 * Description:
 *
 * @Author wggglggg
 * @Create 2023/7/25 19:48
 * @Version 1.0
 */
@WebServlet("/axios02.do")
public class Axios02Servlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StringBuffer stringBuffer = new StringBuffer("");
        BufferedReader bufferedReader = request.getReader();
        String str = null ;
        while((str=bufferedReader.readLine())!=null){
            stringBuffer.append(str);
        }
        str = stringBuffer.toString() ;

        //已知 String
        //需要转化成 Java Object

        Gson gson = new Gson();
        //Gson有两个API
        //1.fromJson(string,T) 将字符串转化成java object
        //2.toJson(java Object) 将java object转化成json字符串，这样才能响应给客户端

        User user = gson.fromJson(str, User.class);

        System.out.println(user);
    }
}
