package com.javaweb.book.dao;

import com.javaweb.book.bean.CartItem;
import com.javaweb.book.bean.User;

import java.util.List;

/**
 * ClassName: CartDAO
 * Description:
 *
 * @Author wggglggg
 * @Create 2023/7/21 18:54
 * @Version 1.0
 */
public interface CartItemDAO {
    //新增购物车项
    void addCarItem(CartItem cartItem);
    //修改特定的购物车项
    void updateCarItem(CartItem cartItem);

    //获取特定用户的所有购物车项
    List<CartItem> getCartItemList(User user);

    //删除特定的购物车项
    void delCartItem(CartItem cartItem);

}
