package com.javaweb.qqzone.dao;

import com.javaweb.qqzone.bean.Reply;
import com.javaweb.qqzone.bean.Topic;

import java.util.List;

/**
 * ClassName: ReplyDAO
 * Description:
 *
 * @Author wggglggg
 * @Create 2023/7/17 12:47
 * @Version 1.0
 */
public interface ReplyDAO {
    //获取指定日志的回复列表
    List<Reply> getReplyList(Topic topic);
    //添加回复
    void addReply(Topic topic);
    //删除回复
    void delReply(Topic topic);
}
