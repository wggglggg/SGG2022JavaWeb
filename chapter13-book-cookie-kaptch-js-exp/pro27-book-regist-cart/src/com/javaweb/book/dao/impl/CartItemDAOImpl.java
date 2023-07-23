package com.javaweb.book.dao.impl;

import com.javaweb.base.BaseDAO;
import com.javaweb.book.bean.CartItem;
import com.javaweb.book.bean.User;
import com.javaweb.book.dao.CartItemDAO;

import java.util.List;

/**
 * ClassName: CartItemDAOImpl
 * Description:
 *
 * @Author wggglggg
 * @Create 2023/7/21 18:57
 * @Version 1.0
 */
public class CartItemDAOImpl extends BaseDAO<CartItem> implements CartItemDAO {
    @Override
    public void addCarItem(CartItem cartItem) {
        String sql = "insert into t_cart_item values(0, ?, ?, ?) ";
        executeUpdate(sql, cartItem.getBook().getId(), cartItem.getBuyCount(), cartItem.getUserBean().getId());

    }

    @Override
    public void updateCarItem(CartItem cartItem) {
        String sql = "update t_cart_item set buyCount = ? where id = ?";
        executeUpdate(sql, cartItem.getBuyCount(), cartItem.getId());
    }

    @Override
    public List<CartItem> getCartItemList(User user) {
        String sql = "select * from t_cart_item where userBean = ?";
        return executeQuery(sql, user.getId());
    }

    @Override
    public void delCartItem(CartItem cartItem) {
        String sql = "delete from t_cart_item where id = ?";
        executeUpdate(sql, cartItem.getId());
    }


}
