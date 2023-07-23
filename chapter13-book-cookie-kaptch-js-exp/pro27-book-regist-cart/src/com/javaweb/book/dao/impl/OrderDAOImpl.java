package com.javaweb.book.dao.impl;

import com.javaweb.base.BaseDAO;
import com.javaweb.book.bean.OrderBean;
import com.javaweb.book.bean.User;
import com.javaweb.book.dao.OrderDAO;

import java.math.BigDecimal;
import java.util.List;

/**
 * ClassName: OrderDAOImpl
 * Description:
 *
 * @Author wggglggg
 * @Create 2023/7/22 16:16
 * @Version 1.0
 */
public class OrderDAOImpl extends BaseDAO<OrderBean> implements OrderDAO {

    @Override
    public void addOrder(OrderBean orderBean) {
        String sql = "insert into t_order values(0, ?, ?, ?, ?, ?)";
        int orderBeanId = executeUpdate(sql, orderBean.getOrderNo(), orderBean.getOrderDate(),
                orderBean.getOrderUser().getId(), orderBean.getOrderMoney(), 0);
        // orderBeanId接受到后,设置给orderBean,这时添加到orderItem的图书才能通过orderBean.getOrderBean().getId()拿到ID

        orderBean.setId(orderBeanId);
    }

    @Override
    public List<OrderBean> getOrderList(User user) {
        String sql = "select * from t_order where orderUser = ? ";

        return executeQuery(sql, user.getId());
    }

    // 从
    @Override
    public Integer getOrderTotalBookCount(OrderBean orderBean) {
        String sql = "SELECT SUM(buyCount), orderBean FROM t_order JOIN t_order_item ON t_order.`id` = t_order_item.`orderBean` WHERE orderUser = ? AND orderBean = ?\n" +
                "GROUP BY orderBean; ";

        return ((BigDecimal) executeComplexQuery(sql, orderBean.getOrderUser().getId(), orderBean.getId())[0]).intValue();
    }


}
