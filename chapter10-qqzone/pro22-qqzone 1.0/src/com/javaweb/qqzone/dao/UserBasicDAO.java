package com.javaweb.qqzone.dao;

import com.javaweb.qqzone.bean.UserBasic;

import java.util.List;

/**
 * IntefaceName: UserBasicDAO
 * Description:
 *
 * @Author wggglggg
 * @Create 2023/7/17 12:46
 * @Version 1.0
 */
public interface UserBasicDAO {
    //根据账号和密码获取特定用户信息
    UserBasic getUserBasic(String loginId, String pwd);
    //获取指定用户的所有好友列表
    List<UserBasic> getUserBasicList(UserBasic userBasic);
    //根据id查询UserBasic的信息
    UserBasic getUserBasicById(Integer id);

}
