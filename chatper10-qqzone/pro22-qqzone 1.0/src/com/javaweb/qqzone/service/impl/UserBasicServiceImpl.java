package com.javaweb.qqzone.service.impl;

import com.javaweb.qqzone.bean.UserBasic;
import com.javaweb.qqzone.dao.UserBasicDAO;
import com.javaweb.qqzone.service.UserBasicService;

import java.util.List;

/**
 * ClassName: UserBasicServiceImpl
 * Description:
 *
 * @Author wggglggg
 * @Create 2023/7/17 13:42
 * @Version 1.0
 */
public class UserBasicServiceImpl implements UserBasicService {

    UserBasicDAO userBasicDAO = null;
    @Override
    public UserBasic login(String loginId, String pwd) {
        UserBasic userBasic = userBasicDAO.getUserBasic(loginId, pwd);
        return userBasic;
    }

    @Override
    public List<UserBasic> getFriendList(UserBasic userBasic) {
        List<UserBasic> userBasicList = userBasicDAO.getUserBasicList(userBasic);
        return userBasicList;
    }

    @Override
    public UserBasic getFriendById(Integer id) {
        return userBasicDAO.getUserBasicById(id);
    }

    public UserBasic getUserBasicById(Integer id){
        return userBasicDAO.getUserBasicById(id);
    }


}
