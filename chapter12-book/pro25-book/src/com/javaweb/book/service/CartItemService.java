package com.javaweb.book.service;

import com.javaweb.book.bean.Cart;
import com.javaweb.book.bean.CartItem;
import com.javaweb.book.bean.User;

import java.util.List;

/**
 * ClassName: CarItemService
 * Description:
 *
 * @Author wggglggg
 * @Create 2023/7/21 15:43
 * @Version 1.0
 */
public interface CartItemService {
    void addCartItem(CartItem cartItem);
    void updateCartItem(CartItem cartItem);
    void addOrUpdateCartItem(CartItem cartItem, Cart cart);

    //加载特定用户的购物车信息
    Cart getCart(User user);
    //获取指定用户的所有购物车项列表（需要注意的是，这个方法内部查询的时候，会将book的详细信息设置进去）
    List<CartItem> getCartItemList(User user);
}
