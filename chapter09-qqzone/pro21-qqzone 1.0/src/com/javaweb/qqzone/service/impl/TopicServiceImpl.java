package com.javaweb.qqzone.service.impl;

import com.javaweb.qqzone.bean.Topic;
import com.javaweb.qqzone.bean.UserBasic;
import com.javaweb.qqzone.dao.TopicDAO;
import com.javaweb.qqzone.service.TopicService;

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

    @Override
    public List<Topic> getTopicList(UserBasic userBasic) {
        List<Topic> topicList = topicDAO.getTopicList(userBasic);
        return topicList;
    }
}
