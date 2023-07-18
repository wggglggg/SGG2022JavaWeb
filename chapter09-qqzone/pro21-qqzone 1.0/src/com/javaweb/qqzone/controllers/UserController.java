package com.javaweb.qqzone.controllers;

import com.javaweb.qqzone.bean.Topic;
import com.javaweb.qqzone.bean.UserBasic;
import com.javaweb.qqzone.service.TopicService;
import com.javaweb.qqzone.service.UserBasicService;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * ClassName: UserController
 * Description:
 *
 * @Author wggglggg
 * @Create 2023/7/17 14:03
 * @Version 1.0
 */
public class UserController {
    UserBasicService userBasicService = null;
    TopicService topicService = null;

    public String login(String loginId, String pwd, HttpSession session){
        UserBasic userBasic = userBasicService.login(loginId, pwd);

        if (userBasic != null){
            List<UserBasic> friendList = userBasicService.getFriendList(userBasic);
            List<Topic> topicList = topicService.getTopicList(userBasic);

            userBasic.setFriendList(friendList);
            userBasic.setTopicList(topicList);

            session.setAttribute("userBasic", userBasic);

            return "index";
        }
        return "login";
    }



}
