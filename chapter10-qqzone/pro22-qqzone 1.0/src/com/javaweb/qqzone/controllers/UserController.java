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

            //userBasic这个key保存的是登陆者的信息
            //friend这个key保存的是当前进入的是谁的空间
            session.setAttribute("userBasic", userBasic);
            session.setAttribute("friend", userBasic);

            return "index";
        }
        return "login";
    }

    public String friend(Integer id, HttpSession session){
        //1.根据id获取指定的用户信息
        UserBasic friend = userBasicService.getFriendById(id);

        List<Topic> TopicList = topicService.getTopicList(friend);

        friend.setTopicList(TopicList);

        session.setAttribute("friend", friend);

        return "index";
    }


}
