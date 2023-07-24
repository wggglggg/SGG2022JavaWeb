package com.javaweb.book.service.impl;

import com.javaweb.book.bean.CartItem;
import com.javaweb.book.bean.OrderBean;
import com.javaweb.book.bean.OrderItem;
import com.javaweb.book.bean.User;
import com.javaweb.book.dao.CartItemDAO;
import com.javaweb.book.dao.OrderDAO;
import com.javaweb.book.dao.OrderItemDAO;
import com.javaweb.book.service.OrderService;

import java.util.List;
import java.util.Map;

/**
 * ClassName: OrderServiceImpl
 * Description:
 *
 * @Author wggglggg
 * @Create 2023/7/22 16:21
 * @Version 1.0
 */
public class OrderServiceImpl implements OrderService {

    OrderDAO orderDAO;
    OrderItemDAO orderItemDAO;
    CartItemDAO cartItemDAO;
    @Override
    public void addOrder(OrderBean orderBean) {
        //1) 订单表添加一条记录
        //2) 订单详情表添加7条记录
        //3) 购物车项表中需要删除对应的7条记录

        //1) 订单表添加一条记录
        orderDAO.addOrder(orderBean);

        //2) 订单详情表添加7条记录
        //orderBean中的orderItemList是空的，此处我们应该根据用户的购物车中的购物车项去转换成一个一个的订单项
        User orderUser = orderBean.getOrderUser();
        Map<Integer, CartItem> cartItemMap = orderUser.getCart().getCartItemMap();

        for (CartItem cartItem : cartItemMap.values()){
            OrderItem orderItem = new OrderItem();
            orderItem.setBook(cartItem.getBook());
            orderItem.setBuyCount(cartItem.getBuyCount());
            orderItem.setOrderBean(orderBean);
            orderItemDAO.addOrderItem(orderItem);
        }

        //3) 购物车项表中需要删除对应的7条记录
        for (CartItem cartItem : cartItemMap.values()){
            cartItemDAO.delCartItem(cartItem);
        }

    }

    @Override
    public List<OrderBean> getOrderList(User user) {
        List<OrderBean> orderList = orderDAO.getOrderList(user);

        for (OrderBean order : orderList){
            Integer orderTotalBookCount = orderDAO.getOrderTotalBookCount(order);
            order.setTotalBookCount(orderTotalBookCount);
        }

        return orderList;
    }
}
