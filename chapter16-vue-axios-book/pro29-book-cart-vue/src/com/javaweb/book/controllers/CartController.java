package com.javaweb.book.controllers;

import com.google.gson.Gson;
import com.javaweb.book.bean.Book;
import com.javaweb.book.bean.Cart;
import com.javaweb.book.bean.CartItem;
import com.javaweb.book.bean.User;
import com.javaweb.book.service.CartItemService;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * ClassName: CartController
 * Description:
 *
 * @Author wggglggg
 * @Create 2023/7/21 15:28
 * @Version 1.0
 */
public class CartController {
    private CartItemService cartItemService;

    public String addCart(Integer bookId, HttpSession session){
        User user = (User) session.getAttribute("currentUser");
        CartItem cartItem = new CartItem(new Book(bookId), 1, user);

        Cart cart = user.getCart();
        //将指定的图书添加到当前用户的购物车中
        cartItemService.addOrUpdateCartItem(cartItem, cart);

        return "redirect:cart.do";
    }

    //加载当前用户的购物车信息
    public String index(HttpSession session){
        User user = (User) session.getAttribute("currentUser");
        Cart cart = cartItemService.getCart(user);
        user.setCart(cart);
        session.setAttribute("currentUser", user);


        return "cart/cart";
    }

    public String editCart(Integer cartItemId, Integer buyCount){
        cartItemService.updateCartItem(new CartItem(cartItemId, buyCount));

//        return "redirect:cart.do";
        return "";

    }

    public String cartInfo(HttpSession session){
        User user = (User) session.getAttribute("currentUser");
        Cart cart = cartItemService.getCart(user);
        //调用Cart中的三个属性的get方法，目的是在此处计算这三个属性的值，否则这三个属性为null，
        //导致的结果就是下一步的gson转化时，为null的属性会被忽略

        cart.getTotalBooks();
        cart.getTotalMoney();
        cart.getTotalCount();


        Gson gson = new Gson();
        String cartJsonStr = gson.toJson(cart);
        return "json:" + cartJsonStr;
    }

}
