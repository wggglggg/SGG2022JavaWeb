package com.javaweb.listeners;

import com.javaweb.ioc.BeanFactory;
import com.javaweb.ioc.ClassPathXmlApplicationContext;

import javax.management.MBeanServerFactory;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * ClassName: ContextLoaderListener
 * Description:
 *
 * @Author wggglggg
 * @Create 2023/7/15 21:20
 * @Version 1.0
 */
@WebListener
public class ContextLoaderListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        //1.获取ServletContext对象
        ServletContext servletContext = servletContextEvent.getServletContext();

        //2.获取上下文的初始化参数, 获取配置文件的地址
        String appContextXmlPath = servletContext.getInitParameter("contextConfigLocation");

        //3.创建IOC容器
         BeanFactory beanFactory = new ClassPathXmlApplicationContext(appContextXmlPath);
        //4.将IOC容器保存到application作用域
         servletContext.setAttribute("beanFactory", beanFactory);

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
