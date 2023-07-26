package com.javaweb.filters;

import com.javaweb.book.bean.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * ClassName: SessionFilter
 * Description:
 *
 * @Author wggglggg
 * @Create 2023/7/23 10:29
 * @Version 1.0
 */

//@WebFilter(urlPatterns = {"*.do"},
//        initParams = {@WebInitParam(
//                name = "white",
//                value = "/pro27/page.do?operate=page&page=user/login," +
//                        "/pro27/page.do?operate=page&page=user/regist," +
//                        "/pro27/user.do?*" +
//                        "/pro27/user.do?null," +
//                        "/pro27/book.do?null,"  +
//                        "/pro27/cart.do?null"
//
//        )})
public class SessionFilter implements Filter {
    List<String> whiteList;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String white = filterConfig.getInitParameter("white");
        String[] whiteArr = white.split(",");
        whiteList = Arrays.asList(whiteArr);

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        //http://localhost:8080/    pro25/page.do?operate=page&page=user/login
        String requestURI = request.getRequestURI();
        System.out.println("requestURI = " + requestURI);
        String queryString = request.getQueryString();
        System.out.println("queryString = " + queryString);

        String pathStr = requestURI + "?" + queryString;

        if (whiteList.contains(pathStr)){

            filterChain.doFilter(request, response);

        }else {
            HttpSession session = request.getSession();
            User currentUser = (User) session.getAttribute("currentUser");



            if (currentUser == null){
                response.sendRedirect("page.do?operate=page&page=user/login");
            }else {
                filterChain.doFilter(request, response);
            }
        }




    }

    @Override
    public void destroy() {

    }
}
