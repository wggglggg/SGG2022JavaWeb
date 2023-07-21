package com.javaweb.book.service.impl;

import com.javaweb.book.bean.User;
import com.javaweb.book.dao.UserDAO;
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
    @Override
    public User login(String uname, String pwd) {

        return userDAO.getUser(uname, pwd);
    }
}
