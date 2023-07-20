package com.javaweb.qqzone.dao.impl;

import com.javaweb.qqzone.bean.Reply;
import com.javaweb.qqzone.bean.Topic;
import com.javaweb.qqzone.dao.ReplyDAO;
import com.javaweb.base.BaseDAO;

import java.util.List;

/**
 * ClassName: ReplyDAOImpl
 * Description:
 *
 * @Author wggglggg
 * @Create 2023/7/18 21:57
 * @Version 1.0
 */
public class ReplyDAOImpl extends BaseDAO<Reply> implements ReplyDAO {

    @Override
    public List<Reply> getReplyList(Topic topic) {
        String sql = "select * from t_reply where topic = ?";
        return executeQuery(sql, topic.getId());
    }

    @Override
    public void addReply(Reply reply) {
        String sql = "insert into t_reply values(0,?,?,?,?)";
        executeUpdate(sql, reply.getContent(), reply.getReplyDate(),
                reply.getAuthor().getId(), reply.getTopic().getId());
    }

    @Override
    public void delReply(Integer id) {
        String sql = "delete from t_reply where id = ?";

        executeUpdate(sql, id);
    }
}
