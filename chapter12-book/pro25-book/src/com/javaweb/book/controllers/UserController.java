package com.javaweb.book.controllers;

import com.javaweb.book.bean.Cart;
import com.javaweb.book.bean.User;
import com.javaweb.book.service.CartItemService;
import com.javaweb.book.service.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;

/**
 * ClassName: UserController
 * Description:
 *
 * @Author wggglggg
 * @Create 2023/7/21 9:37
 * @Version 1.0
 */
public class UserController {

    private UserService userService;
    private CartItemService cartItemService;

    public String login(String uname, String pwd, HttpSession session, HttpServletRequest request, HttpServletResponse response){
        User user = userService.login(uname, pwd);

        Cookie cookie = new Cookie(uname, pwd);
        cookie.setMaxAge(60*60*24*10);
        response.addCookie(cookie);

        if (user != null){
            Cart cart = cartItemService.getCart(user);
            user.setCart(cart);
            session.setAttribute("currentUser", user);
            return "redirect:book.do";
        }
        return "user/login";
    }

    public String index (HttpSession session){

        User user = (User) session.getAttribute("currentUser");
        if (user != null){
            Cart cart = cartItemService.getCart(user);
            user.setCart(cart);
            session.setAttribute("currentUser", user);
            return "redirect:book.do";
        }
        return "user/login";
    }
}
