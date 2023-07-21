package com.javaweb.book.bean;

/**
 * ClassName: OrderItem
 * Description:
 *
 * @Author wggglggg
 * @Create 2023/7/20 21:51
 * @Version 1.0
 */
public class OrderItem {
    private Integer id;
    private Book book;      // 多个订单详单中会出现 N : 1   1本书红楼梦
    private Integer buyCount;
    private OrderBean orderBean; //  多个详单详单 N ： 1 个订单

    public OrderItem() {
    }

    public OrderItem(Integer id) {
        this.id = id;
    }

    public OrderItem(Integer id, Book book, Integer buyCount, OrderBean orderBean) {
        this.id = id;
        this.book = book;
        this.buyCount = buyCount;
        this.orderBean = orderBean;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Integer getBuyCount() {
        return buyCount;
    }

    public void setBuyCount(Integer buyCount) {
        this.buyCount = buyCount;
    }

    public OrderBean getOrderBean() {
        return orderBean;
    }

    public void setOrderBean(OrderBean orderBean) {
        this.orderBean = orderBean;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "id=" + id +
                ", book=" + book +
                ", buyCount=" + buyCount +
                ", orderBean=" + orderBean +
                '}';
    }
}
