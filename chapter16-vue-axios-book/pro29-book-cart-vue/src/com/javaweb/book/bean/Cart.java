package com.javaweb.book.bean;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * ClassName: Cart
 * Description:
 *
 * @Author wggglggg
 * @Create 2023/7/21 19:09
 * @Version 1.0
 */
public class Cart {
    private Double totalMoney;      //购物车的总金额
    private Integer totalCount;     //购物车中购物项的数量, 有几条数据,相同信息为一条
    private Integer totalBooks;     //购物车中书本的总数量，而不是购物车项的总数量

    private Map<Integer, CartItem> cartItemMap; //购物车中购物车项的集合,也就是N条数据总集

    public Cart() {
    }

    public Double getTotalMoney() {
        totalMoney = 0.0;
        if (cartItemMap != null && cartItemMap.size() > 0){
            Set<Map.Entry<Integer, CartItem>> entries = cartItemMap.entrySet();

            for (Map.Entry<Integer, CartItem> cartItemEntry : entries){
                CartItem cartItem = cartItemEntry.getValue();
                BigDecimal bigDecimalBuyCount = new BigDecimal(cartItem.getBuyCount() + "");
                BigDecimal bigDecimalBookPrice = new BigDecimal(cartItem.getBook().getPrice() + "");
                BigDecimal bigDecimalTotalMoney = bigDecimalBuyCount.multiply(bigDecimalBookPrice);
                Double totalMoneyDouble = bigDecimalTotalMoney.doubleValue();
                totalMoney += totalMoneyDouble ;
            }
        }
        return totalMoney;
    }

    public void setTotalMoney(Double totalMoney) {
        this.totalMoney = totalMoney;
    }

    // 购物车里有几条数据,
    public Integer getTotalCount() {
        totalCount = 0;
        if (cartItemMap != null && cartItemMap.size() > 0){
            totalCount = cartItemMap.size();
        }

        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    // 购物车内有多少本书
    public Integer getTotalBooks() {
        totalBooks = 0;
        if (cartItemMap != null && cartItemMap.size() > 0){
            Collection<CartItem> cartItems = cartItemMap.values();
            for (CartItem cartItem : cartItems){
                totalBooks += cartItem.getBuyCount();
            }
        }

        return totalBooks;
    }

    public void setTotalBooks(Integer totalBooks) {
        this.totalBooks = totalBooks;
    }

    public Map<Integer, CartItem> getCartItemMap() {
        return cartItemMap;
    }

    public void setCartItemMap(Map<Integer, CartItem> cartItemMap) {
        this.cartItemMap = cartItemMap;
    }
}
