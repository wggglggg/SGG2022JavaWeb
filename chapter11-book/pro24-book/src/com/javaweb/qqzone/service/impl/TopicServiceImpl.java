package com.javaweb.qqzone.service.impl;

import com.javaweb.qqzone.bean.Reply;
import com.javaweb.qqzone.bean.Topic;
import com.javaweb.qqzone.bean.UserBasic;
import com.javaweb.qqzone.dao.TopicDAO;
import com.javaweb.qqzone.dao.UserBasicDAO;
import com.javaweb.qqzone.service.ReplyService;
import com.javaweb.qqzone.service.TopicService;
import com.javaweb.qqzone.service.UserBasicService;

import java.util.List;

/**
 * ClassName: TopicServiceImpl
 * Description:
 *
 * @Author wggglggg
 * @Create 2023/7/17 13:49
 * @Version 1.0
 */
public class TopicServiceImpl implements TopicService {
        TopicDAO topicDAO = null;
        UserBasicService userBasicService = null;
        ReplyService replyService = null;


    @Override
    public List<Topic> getTopicList(UserBasic userBasic) {
        List<Topic> topicList = topicDAO.getTopicList(userBasic);
        return topicList;
    }

    @Override
    public Topic getTopicById(Integer id) {
        Topic topic = getTopic(id);
//        List<Reply> replyList = replyService.getReplyListByTopicId(id);
        List<Reply> replyList = replyService.getReplyListByTopicId(topic.getId());

        topic.setReplyList(replyList);
        return topic;
    }

    @Override
    public Topic getTopic(Integer id) {
        Topic topic = topicDAO.getTopic(id);
        UserBasic author = topic.getAuthor();
        author = userBasicService.getUserBasicById(author.getId());

        topic.setAuthor(author);
        return topic;
    }

    @Override
    public void delTopicById(Integer id) {
        Topic topic = topicDAO.getTopic(id);
        if (topic != null){
            replyService.delReplyList(topic);
            topicDAO.delTopic(topic);
        }
    }

    @Override
    public void addTopic(Topic topic) {
        topicDAO.addTopic(topic);
    }


}
