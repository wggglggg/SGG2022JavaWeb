package com.javaweb.book.controllers;

import com.javaweb.book.bean.Cart;
import com.javaweb.book.bean.User;
import com.javaweb.book.service.CartItemService;
import com.javaweb.book.service.UserService;
import org.junit.Test;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
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

    public String register(String registerName, String registerPwd, String registerEmail, String registerKaptch,
                           HttpSession session, HttpServletResponse response) throws IOException {
        Object kaptcha = session.getAttribute("KAPTCHA_SESSION_KEY");
        if (kaptcha == null || !registerKaptch.equals(kaptcha)){
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter responseWriter = response.getWriter();
            responseWriter.println("<script>alert('验证码不正确');window.location.href='page.do?operate=page&page=user/regist'</script> ");
            return "user/regist";

        }else {
            if (registerKaptch.equals(kaptcha)){
                User user = new User(registerName, registerPwd, registerEmail, 0);
                userService.regist(user);
                return "user/login";

            }
        }
        return "user/login";
    }
    public String ckUname(String uname){
        User user = userService.getUser(uname);
        if(user!=null){
            //用户名已经被占用，不可以注册
            return "json:{'uname':'1'}";
            //return "ajax:1";
        }else{
            //用户名可以注册
            return "json:{'uname':'0'}";
            //return "ajax:0";
        }
    }

}
