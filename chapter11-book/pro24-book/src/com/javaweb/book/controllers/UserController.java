package com.javaweb.book.controllers;

import com.javaweb.book.bean.User;
import com.javaweb.book.service.UserService;

import javax.servlet.http.HttpSession;

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

    public String login(String uname, String pwd, HttpSession session){
        User user = userService.login(uname, pwd);
        if (user != null){
            session.setAttribute("currentUser", user);
        }

        return "redirect:book.do";

    }
}
