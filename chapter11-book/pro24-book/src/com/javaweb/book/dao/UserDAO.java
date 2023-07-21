package com.javaweb.book.dao;

import com.javaweb.book.bean.User;

/**
 * ClassName: UserDAO
 * Description:
 *
 * @Author wggglggg
 * @Create 2023/7/21 9:42
 * @Version 1.0
 */
public interface UserDAO {

    User getUser(String uname, String pwd);
}
