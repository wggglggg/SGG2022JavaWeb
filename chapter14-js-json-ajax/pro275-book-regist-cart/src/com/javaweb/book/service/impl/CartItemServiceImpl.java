package com.javaweb.book.service.impl;

import com.javaweb.book.bean.Book;
import com.javaweb.book.bean.Cart;
import com.javaweb.book.bean.CartItem;
import com.javaweb.book.bean.User;
import com.javaweb.book.dao.CartItemDAO;
import com.javaweb.book.service.BookService;
import com.javaweb.book.service.CartItemService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ClassName: CartItemServiceImpl
 * Description:
 *
 * @Author wggglggg
 * @Create 2023/7/21 19:06
 * @Version 1.0
 */
public class CartItemServiceImpl implements CartItemService {
    private CartItemDAO cartItemDAO;
    private BookService bookService;

    @Override
    public void addCartItem(CartItem cartItem) {
        cartItemDAO.addCarItem(cartItem);
    }

    @Override
    public void updateCartItem(CartItem cartItem) {
        cartItemDAO.updateCarItem(cartItem);
    }

    @Override
    public void addOrUpdateCartItem(CartItem cartItem, Cart cart) {
        if (cart != null){
            Map<Integer, CartItem> cartItemMap = cart.getCartItemMap();

            // 如果购物车从没使用过,要先造一个Map,用来存储cartItem
            if (cartItemMap == null){
                cartItemMap = new HashMap<>();
            }
            // 添加商品时,如果购物车内有相同的商品,就将数量+1
            if (cartItemMap.containsKey(cartItem.getBook().getId())){
                CartItem cartItemTemp = cartItemMap.get(cartItem.getBook().getId());
                cartItemTemp.setBuyCount(cartItemTemp.getBuyCount() + 1);
                updateCartItem(cartItemTemp);
            } else {
                // 添加商品时,如果购物车中没有相同的商品
                addCartItem(cartItem);
            }

        } else {
            addCartItem(cartItem);  // 没有购物车
        }
    }

    @Override
    public Cart getCart(User user) {
        List<CartItem> cartItemList = getCartItemList(user);

        Map<Integer, CartItem> cartItemMap = new HashMap<>();
        for (CartItem cartItem : cartItemList){
            cartItemMap.put(cartItem.getBook().getId(), cartItem);
        }
        Cart cart = new Cart();
        cart.setCartItemMap(cartItemMap);

        return cart;
    }

    @Override
    public List<CartItem> getCartItemList(User user) {
        List<CartItem> cartItemList = cartItemDAO.getCartItemList(user);

        for (CartItem cartItem : cartItemList){
            Book book = bookService.getBook(cartItem.getBook().getId());
            cartItem.setBook(book);
        }

        return cartItemList;
    }
}
