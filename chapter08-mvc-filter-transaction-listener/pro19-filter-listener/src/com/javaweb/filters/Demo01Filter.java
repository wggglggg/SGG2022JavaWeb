package com.javaweb.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import java.io.IOException;

/**
 * ClassName: Demo01Filter
 * Description:
 *
 * @Author wggglggg
 * @Create 2023/7/15 9:12
 * @Version 1.0
 */
@WebFilter("*.do")
public class Demo01Filter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println(" doFilterA ");
        //放行
        filterChain.doFilter(servletRequest,servletResponse);
        System.out.println(" doFilterA2");

    }

    @Override
    public void destroy() {

    }
}
