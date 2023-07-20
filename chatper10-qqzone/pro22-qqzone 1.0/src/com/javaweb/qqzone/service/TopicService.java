package com.javaweb.qqzone.service;

import com.javaweb.qqzone.bean.Topic;
import com.javaweb.qqzone.bean.UserBasic;

import java.util.List;

/**
 * ClassName: TopicService
 * Description:
 *
 * @Author wggglggg
 * @Create 2023/7/17 13:50
 * @Version 1.0
 */
public interface TopicService {
    List<Topic> getTopicList(UserBasic userBasic);

    Topic getTopicById(Integer id);

    Topic getTopic(Integer id);

    void delTopicById(Integer id);

    void addTopic(Topic topic);
}
