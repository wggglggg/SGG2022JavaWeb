package com.javaweb.book.dao.impl;

import com.javaweb.base.BaseDAO;
import com.javaweb.book.bean.OrderItem;
import com.javaweb.book.dao.OrderItemDAO;

/**
 * ClassName: OrderItemDAOImpl
 * Description:
 *
 * @Author wggglggg
 * @Create 2023/7/22 16:41
 * @Version 1.0
 */
public class OrderItemDAOImpl extends BaseDAO<OrderItem> implements OrderItemDAO {
    @Override
    public void addOrderItem(OrderItem orderItem) {
        String sql = "insert into t_order_item values(0, ?, ?, ?)";
        executeUpdate(sql, orderItem.getBook().getId(), orderItem.getBuyCount(),
                orderItem.getOrderBean().getId());
    }
}
