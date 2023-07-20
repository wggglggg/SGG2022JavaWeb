package com.javaweb.qqzone.dao.impl;

import com.javaweb.qqzone.bean.UserBasic;
import com.javaweb.qqzone.dao.UserBasicDAO;
import com.javaweb.base.BaseDAO;

import java.util.List;

/**
 * ClassName: UserBasicDAOImpl
 * Description:
 *
 * @Author wggglggg
 * @Create 2023/7/17 13:10
 * @Version 1.0
 */
public class UserBasicDAOImpl extends BaseDAO<UserBasic> implements UserBasicDAO {
    @Override
    public UserBasic getUserBasic(String loginId, String pwd) {
        String sql = "select * from t_user_basic where loginId = ? and pwd = ?";
        return load(sql, loginId, pwd);

    }

    @Override
    public List<UserBasic> getUserBasicList(UserBasic userBasic) {
        String sql = "SELECT * FROM t_user_basic WHERE id IN (SELECT fid FROM t_friend WHERE uid = ? )";

        return executeQuery(sql, userBasic.getId());
    }


    @Override
    public UserBasic getUserBasicById(Integer id) {
        String sql = "select * from t_user_basic where id = ?";
        return load(sql, id);
    }
}
