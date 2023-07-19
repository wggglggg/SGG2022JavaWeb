package com.javaweb.qqzone.service;

import com.javaweb.qqzone.bean.HostReply;

/**
 * ClassName: ReplyService
 * Description:
 *
 * @Author wggglggg
 * @Create 2023/7/19 9:30
 * @Version 1.0
 */
public interface HostReplyService {

    HostReply getHostReplyByReplyId(Integer replyId);

    void delHostReplyByReplyId(Integer replyId);
}
