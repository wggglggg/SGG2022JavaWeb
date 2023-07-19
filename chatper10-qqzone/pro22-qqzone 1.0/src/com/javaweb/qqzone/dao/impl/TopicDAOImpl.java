package com.javaweb.qqzone.dao.impl;

import com.javaweb.qqzone.bean.Topic;
import com.javaweb.qqzone.bean.UserBasic;
import com.javaweb.qqzone.dao.TopicDAO;
import com.javaweb.qqzone.dao.base.BaseDAO;

import java.util.List;

/**
 * ClassName: TopicDAOImpl
 * Description:
 *
 * @Author wggglggg
 * @Create 2023/7/17 13:18
 * @Version 1.0
 */
public class TopicDAOImpl extends BaseDAO<Topic> implements TopicDAO {
    @Override
    public List<Topic> getTopicList(UserBasic userBasic) {
        String sql = "select * from t_topic where author = ?";


        return executeQuery(sql, userBasic.getId());
    }

    @Override
    public void addTopic(Topic topic) {
        String sql = "insert into t_topic(title, content, topicDate, author) values(?,?,?,?)";
        executeUpdate(sql, topic.getTitle(), topic.getContent(), topic.getTopicDate(), topic.getAuthor());
    }

    @Override
    public void DelTopic(Topic topic) {

    }

    @Override
    public Topic getTopic(Integer id) {
        String sql = "select * from t_topic where id = ?";
        return load(sql, id);
    }
}
