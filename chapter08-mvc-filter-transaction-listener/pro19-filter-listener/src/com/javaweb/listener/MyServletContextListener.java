package com.javaweb.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.net.http.WebSocket;

/**
 * ClassName: MyServletContextListener
 * Description:
 *
 * @Author wggglggg
 * @Create 2023/7/15 21:09
 * @Version 1.0
 */
@WebListener
public class MyServletContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext();
        System.out.println("servletContext = " + servletContext);
        System.out.println("Servlet上下文对象初始化动作被我监听到了....");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("Servlet上下文对象销毁动作被我监听到了.....");
    }
}
