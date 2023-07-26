package com.javaweb.book.controllers;

import com.javaweb.book.bean.OrderBean;
import com.javaweb.book.bean.User;
import com.javaweb.book.service.OrderService;
import org.junit.Test;

import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.List;
import java.util.UUID;

/**
 * ClassName: OrderController
 * Description:
 *
 * @Author wggglggg
 * @Create 2023/7/22 16:51
 * @Version 1.0
 */
public class OrderController {

    private OrderService orderService;

    public String checkout(HttpSession session) {
        OrderBean order = new OrderBean();


        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        long currentTimeMillis = System.currentTimeMillis();
        Date now = new Date(currentTimeMillis);
        String dateStr = sdf.format(now);

        order.setOrderNo(UUID.randomUUID().toString() + "_" + dateStr);
        order.setOrderDate(LocalDateTime.now());


        User currentUser = (User) session.getAttribute("currentUser");
        order.setOrderUser(currentUser);
        order.setOrderMoney(currentUser.getCart().getTotalMoney());
        order.setOrderStatus(0);

        orderService.addOrder(order);


        return "index";
    }

    public String getOrderList(HttpSession session){
        User currentUser = (User) session.getAttribute("currentUser");
        List<OrderBean> orderList = orderService.getOrderList(currentUser);

        currentUser.setOrderBeanList(orderList);

        session.setAttribute("currentUser", currentUser);

        return "order/order";
    }
}
