package com.javaweb.qqzone.service;

import com.javaweb.qqzone.bean.UserBasic;

import java.util.List;

/**
 * ClassName: UserBasicService
 * Description:
 *
 * @Author wggglggg
 * @Create 2023/7/17 13:41
 * @Version 1.0
 */
public interface  UserBasicService {
    // 登陆
    UserBasic login(String loginId, String pwd);

    // 获取某用户的好友列表
    List<UserBasic> getFriendList(UserBasic userBasic);

    UserBasic getFriendById(Integer id);

    UserBasic getUserBasicById(Integer id);

}
