package com.javaweb.book.service;

import com.javaweb.book.bean.OrderBean;
import com.javaweb.book.bean.User;


import java.util.List;

/**
 * ClassName: OrderService
 * Description:
 *
 * @Author wggglggg
 * @Create 2023/7/22 16:20
 * @Version 1.0
 */
public interface OrderService {
    void addOrder(OrderBean orderBean);

    List<OrderBean> getOrderList(User user);

}
