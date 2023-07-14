package com.javaweb.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

/**
 * ClassName: Demo01Servlet
 * Description:
 *
 * @Author wggglggg
 * @Create 2023/7/14 12:38
 * @Version 1.0
 */
@WebServlet(
        urlPatterns = {"/demo01"},
        initParams = {@WebInitParam(name = "Hello", value = "World")}

)
public class Demo01Servlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        ServletConfig servletConfig = getServletConfig();
        // servletConfig = org.apache.catalina.core.StandardWrapperFacade@2cc19e
        System.out.println("servletConfig = " + servletConfig);

        // hello = World, config配置文件设置在web.xml中
        String hello = servletConfig.getInitParameter("Hello");
        System.out.println("hello = " + hello);


        ServletContext servletContext = getServletContext();
        // servletContext = org.apache.catalina.core.ApplicationContextFacade@7475f2db
        System.out.println("servletContext = " + servletContext);
        String contextConfigLocation = servletContext.getInitParameter("contextConfigLocation");
        // contextConfigLocation = classpath:applicationContext.xml
        System.out.println("contextConfigLocation = " + contextConfigLocation);

    }
}
//Servlet生命周期：实例化、初始化、服务、销毁