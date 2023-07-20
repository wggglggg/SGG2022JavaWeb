package com.javaweb.qqzone.dao;

import com.javaweb.qqzone.bean.Topic;
import com.javaweb.qqzone.bean.UserBasic;

import java.util.List;


/**
 * ClassName: TopicDAO
 * Description:
 *
 * @Author wggglggg
 * @Create 2023/7/17 12:47
 * @Version 1.0
 */
public interface TopicDAO {
    //获取指定用户的日志列表
    List<Topic> getTopicList(UserBasic userBasic);
    //添加日志
    void addTopic(Topic topic);
    //删除日志
    void delTopic(Topic topic);
    //获取特定日志信息
    Topic getTopic(Integer Id);

}
