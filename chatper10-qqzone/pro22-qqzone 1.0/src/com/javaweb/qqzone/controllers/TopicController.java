package com.javaweb.qqzone.controllers;

import com.javaweb.qqzone.bean.Reply;
import com.javaweb.qqzone.bean.Topic;
import com.javaweb.qqzone.service.ReplyService;
import com.javaweb.qqzone.service.TopicService;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * ClassName: TopicController
 * Description:
 *
 * @Author wggglggg
 * @Create 2023/7/18 20:26
 * @Version 1.0
 */
public class TopicController {

    TopicService topicService = null;
    ReplyService replyService = null;

    public String topicDetail(Integer id, HttpSession session){
        Topic topic = topicService.getTopicById(id);



        session.setAttribute("topic", topic);

        return "frames/detail";
    }
}
