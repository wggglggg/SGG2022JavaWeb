package com.javaweb.book.dao;

import com.javaweb.book.bean.OrderBean;
import com.javaweb.book.bean.User;

import java.util.List;

/**
 * ClassName: OrderDAO
 * Description:
 *
 * @Author wggglggg
 * @Create 2023/7/22 16:13
 * @Version 1.0
 */
public interface OrderDAO {

    //添加订单
    void addOrder(OrderBean orderBean);

    List<OrderBean> getOrderList(User user);

    Integer getOrderTotalBookCount(OrderBean orderBean);

}
