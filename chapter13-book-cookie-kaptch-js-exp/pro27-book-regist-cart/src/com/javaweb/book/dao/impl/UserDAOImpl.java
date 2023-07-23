package com.javaweb.book.dao.impl;


import com.javaweb.base.BaseDAO;
import com.javaweb.book.bean.User;
import com.javaweb.book.dao.UserDAO;


/**
 * ClassName: UserDAOImpl
 * Description:
 *
 * @Author wggglggg
 * @Create 2023/7/21 9:43
 * @Version 1.0
 */
public class UserDAOImpl extends BaseDAO<User> implements UserDAO {

    @Override
    public User getUser(String uname, String pwd) {
        String sql = "select * from t_user where uname like ? and pwd like ?";
        return load(sql, uname, pwd);
    }

    @Override
    public boolean checkCookie(String uname, String pwd) {
        String sql = "select * from t_user where uname like ? and pwd like ?";
        User user = load(sql, uname, pwd);
        if (user == null){
            return false;
        }
        return true;
    }

    @Override
    public void addUser(User user) {
        String sql = "insert into t_user values(0, ?, ?, ?, 0)";
        executeUpdate(sql, user.getUname(), user.getPwd(), user.getEmail());
    }
}
