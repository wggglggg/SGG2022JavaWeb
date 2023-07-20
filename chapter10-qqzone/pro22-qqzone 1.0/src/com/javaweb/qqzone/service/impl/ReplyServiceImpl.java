package com.javaweb.qqzone.service.impl;

import com.javaweb.qqzone.bean.HostReply;
import com.javaweb.qqzone.bean.Reply;
import com.javaweb.qqzone.bean.Topic;
import com.javaweb.qqzone.bean.UserBasic;
import com.javaweb.qqzone.dao.ReplyDAO;
import com.javaweb.qqzone.dao.UserBasicDAO;
import com.javaweb.qqzone.service.HostReplyService;
import com.javaweb.qqzone.service.ReplyService;
import com.javaweb.qqzone.service.TopicService;
import com.javaweb.qqzone.service.UserBasicService;

import java.util.List;

/**
 * ClassName: ReplyServiceImpl
 * Description:
 *
 * @Author wggglggg
 * @Create 2023/7/19 8:14
 * @Version 1.0
 */
public class ReplyServiceImpl implements ReplyService {

    private ReplyDAO replyDAO = null;

    private UserBasicService userBasicService = null;

    private HostReplyService hostReplyService = null;


    @Override
    public List<Reply> getReplyListByTopicId(Integer topicId) {
        List<Reply> replyList = replyDAO.getReplyList(new Topic(topicId));
        for (int i = 0; i < replyList.size(); i++) {
            Reply reply = replyList.get(i);
            //1.将关联的作者设置进去
            UserBasic author = userBasicService.getUserBasicById(reply.getAuthor().getId());
            reply.setAuthor(author);

            //2.将关联的HostReply设置进去
            HostReply hostReply = hostReplyService.getHostReplyByReplyId(reply.getId());
            reply.setHostReply(hostReply);

        }

        return replyList;
    }

    @Override
    public void addReply(Reply reply) {
        replyDAO.addReply(reply);
    }

    @Override
    public void delReply(Integer id) {
        if (id != null){
//            HostReply hostReply = hostReplyService.getHostReplyByReplyId(id);
            hostReplyService.delHostReplyByReplyId(id);

        }
        replyDAO.delReply(id);
    }

    @Override
    public void delReplyList(Topic topic) {
        List<Reply> replyList = getReplyListByTopicId(topic.getId());
        if (replyList != null){
            for (Reply reply : replyList) {

                delReply(reply.getId());
            }
        }
    }


}
