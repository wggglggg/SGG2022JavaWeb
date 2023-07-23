package com.javaweb.book.service.impl;

import com.javaweb.book.bean.User;
import com.javaweb.book.dao.UserDAO;
import com.javaweb.book.service.CartItemService;
import com.javaweb.book.service.UserService;

/**
 * ClassName: UserServiceImpl
 * Description:
 *
 * @Author wggglggg
 * @Create 2023/7/21 9:41
 * @Version 1.0
 */
public class UserServiceImpl implements UserService {

    private UserDAO userDAO;
    private CartItemService cartItemService;
    @Override
    public User login(String uname, String pwd) {

        return userDAO.getUser(uname, pwd);
    }

    @Override
    public boolean checkCookie(String uname, String pwd) {
        return userDAO.checkCookie(uname, pwd);
    }

    @Override
    public void regist(User user) {
        userDAO.addUser(user);
    }

}
