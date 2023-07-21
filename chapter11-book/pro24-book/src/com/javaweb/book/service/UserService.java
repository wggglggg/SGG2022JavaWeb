package com.javaweb.book.service;

import com.javaweb.book.bean.User;

/**
 * ClassName: UserService
 * Description:
 *
 * @Author wggglggg
 * @Create 2023/7/21 9:39
 * @Version 1.0
 */
public interface UserService {

    User login(String uname, String pwd);

}
