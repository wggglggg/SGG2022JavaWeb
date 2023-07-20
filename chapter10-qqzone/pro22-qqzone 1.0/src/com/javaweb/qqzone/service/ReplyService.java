package com.javaweb.qqzone.service;

import com.javaweb.qqzone.bean.Reply;
import com.javaweb.qqzone.bean.Topic;

import java.util.List;

/**
 * ClassName: ReplyService
 * Description:
 *
 * @Author wggglggg
 * @Create 2023/7/18 22:03
 * @Version 1.0
 */
public interface ReplyService {
    //根据topic的id获取关联的所有的回复
    List<Reply> getReplyListByTopicId(Integer topicId);

    void addReply(Reply reply);

    void delReply(Integer id);

    void delReplyList(Topic topic);
}
