package com.javaweb.qqzone.controllers;

import com.javaweb.qqzone.bean.Reply;
import com.javaweb.qqzone.bean.Topic;
import com.javaweb.qqzone.bean.UserBasic;
import com.javaweb.qqzone.service.ReplyService;
import com.javaweb.qqzone.service.TopicService;

import javax.servlet.http.HttpSession;
import java.sql.Date;
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

    public String delTopic(Integer topicId){
        topicService.delTopicById(topicId);

        return "redirect:topic.do?operate=getTopicList";
    }

    public String getTopicList(HttpSession session){
        //从session中获取当前用户信息
        UserBasic userBasic = (UserBasic) session.getAttribute("userBasic");
        //再次查询当前用户关联的所有的日志
        List<Topic> topicList = topicService.getTopicList(userBasic);
        //设置一下关联的日志列表(因为之前session中关联的friend的topicList和此刻数据库中不一致）
        userBasic.setTopicList(topicList);
        //重新覆盖一下friend中的信息(为什么不覆盖userbasic中？因为main.html页面迭代的是friend这个key中的数据)
        session.setAttribute("friend", userBasic);
        return "frames/main";
    }

    public String addTopic(String title, String content, HttpSession session){
        UserBasic userBasic = (UserBasic) session.getAttribute("userBasic");
        //获取当前时间戳Long类型, 给java.sql.Date()当参数
        long nowDate = System.currentTimeMillis();
        Topic topic = new Topic(title, content, new Date(nowDate), userBasic);
        topicService.addTopic(topic);

        return "redirect:topic.do?operate=getTopicList";
    }
}
