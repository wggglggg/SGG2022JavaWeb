package com.javaweb.book.bean;

import java.math.BigDecimal;

/**
 * ClassName: CartItem
 * Description:
 *
 * @Author wggglggg
 * @Create 2023/7/20 21:53
 * @Version 1.0
 */
public class CartItem {
    private Integer id;
    private Book book;
    private Integer buyCount;
    private User userBean;  // 哪一个用户添加到购物车中的

    private Double cartItemSum;

    public CartItem() {
    }

    public CartItem(Integer id) {
        this.id = id;
    }

    public CartItem(Integer id, Integer buyCount) {
        this.id = id;
        this.buyCount = buyCount;
    }

    public CartItem(Book book, Integer buyCount, User userBean) {
        this.book = book;
        this.buyCount = buyCount;
        this.userBean = userBean;
    }

    public CartItem(Integer id, Book book, Integer buyCount, User userBean) {
        this.id = id;
        this.book = book;
        this.buyCount = buyCount;
        this.userBean = userBean;
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

    public User getUserBean() {
        return userBean;
    }

    public void setUserBean(User userBean) {
        this.userBean = userBean;
    }

    public Double getCartItemSum() {
        // BigDecimal(string),接受字符串,再通过.add sub multiply divide来加减乘除
        BigDecimal bigDecimalPrice = new BigDecimal(book.getPrice() + "");
        BigDecimal bigDecimalBuyCount = new BigDecimal(buyCount + "");
        // 将每本书价格 * 多少本书 = 金额
        BigDecimal xj = bigDecimalPrice.multiply(bigDecimalBuyCount);
        cartItemSum = xj.doubleValue();
        return cartItemSum;
    }

    public void setCartItemSum(Double cartItemSum) {
        this.cartItemSum = cartItemSum;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "id=" + id +
                ", book=" + book +
                ", buyCount=" + buyCount +
                ", userBean=" + userBean +
                '}';
    }
}
