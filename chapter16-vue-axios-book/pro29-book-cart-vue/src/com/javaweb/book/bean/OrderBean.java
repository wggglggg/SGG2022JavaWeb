package com.javaweb.book.bean;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

/**
 * ClassName: OrderBean
 * Description:
 *
 * @Author wggglggg
 * @Create 2023/7/20 21:52
 * @Version 1.0
 */
public class OrderBean {
    private Integer id;
    private String orderNo;
    private Date orderDate;
    private User orderUser;
    private Double orderMoney;
    private Integer orderStatus;

    private Integer totalBookCount;
    private List<OrderItem> orderItemList;   // 一个Order订单 1:N 多本图书

    public OrderBean() {
    }

    public OrderBean(Integer id) {
        this.id = id;
    }

    public OrderBean(Integer id, String orderNo, Date orderDate, User orderUser, Double orderMoney, Integer orderStatus, Integer totalBookCount, List<OrderItem> orderItemList) {
        this.id = id;
        this.orderNo = orderNo;
        this.orderDate = orderDate;
        this.orderUser = orderUser;
        this.orderMoney = orderMoney;
        this.orderStatus = orderStatus;
        this.totalBookCount = totalBookCount;
        this.orderItemList = orderItemList;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public User getOrderUser() {
        return orderUser;
    }

    public void setOrderUser(User orderUser) {
        this.orderUser = orderUser;
    }

    public Double getOrderMoney() {
        return orderMoney;
    }

    public void setOrderMoney(Double orderMoney) {
        this.orderMoney = orderMoney;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public List<OrderItem> getOrderItemList() {
        return orderItemList;
    }

    public void setOrderItemList(List<OrderItem> orderItemList) {

        this.orderItemList = orderItemList;
    }

    public Integer getTotalBookCount() {
        return totalBookCount;
    }

    public void setTotalBookCount(Integer totalBookCount) {
        this.totalBookCount = totalBookCount;
    }

    @Override
    public String toString() {
        return "OrderBean{" +
                "id=" + id +
                ", orderNo='" + orderNo + '\'' +
                ", orderDate=" + orderDate +
                ", orderUser=" + orderUser +
                ", orderMoney=" + orderMoney +
                ", orderStatus=" + orderStatus +
                ", totalBookCount=" + totalBookCount +
                ", orderItemList=" + orderItemList +
                '}';
    }
}
