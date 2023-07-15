package com.javaweb.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * ClassName: Filter03
 * Description:
 *
 * @Author wggglggg
 * @Create 2023/7/15 9:31
 * @Version 1.0
 */
@WebFilter("*.do")
public class Filter03 implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("C");
        filterChain.doFilter(servletRequest, servletResponse);
        System.out.println("C2");
    }

    @Override
    public void destroy() {

    }
}
