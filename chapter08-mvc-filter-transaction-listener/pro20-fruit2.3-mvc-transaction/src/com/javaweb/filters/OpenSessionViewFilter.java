package com.javaweb.filters;

import com.javaweb.trans.TransactionManager;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;

import java.sql.SQLException;

/**
 * ClassName: OpenSessionViewFilter
 * Description:
 *
 * @Author wggglggg
 * @Create 2023/7/15 15:18
 * @Version 1.0
 */
@WebFilter("*.do")
public class OpenSessionViewFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain){
        try {

            TransactionManager.beginTrans();
            System.out.println("开启事务....");
            filterChain.doFilter(servletRequest, servletResponse);
            TransactionManager.commit();
            System.out.println("提交事务...");
        } catch (Exception e) {
            e.printStackTrace();
            try {
                System.out.println("回滚事务....");
                TransactionManager.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void destroy() {

    }
}
