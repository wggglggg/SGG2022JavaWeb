package com.javaweb.book.dao;

import com.javaweb.book.bean.OrderItem;

/**
 * ClassName: OrderItemDAO
 * Description:
 *
 * @Author wggglggg
 * @Create 2023/7/22 16:39
 * @Version 1.0
 */
public interface OrderItemDAO {
    //添加订单项,就是添加订单详情里面的第一行数据
    void addOrderItem(OrderItem orderItem);
}
