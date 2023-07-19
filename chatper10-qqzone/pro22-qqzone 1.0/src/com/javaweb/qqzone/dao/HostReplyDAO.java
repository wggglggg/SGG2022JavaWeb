package com.javaweb.qqzone.dao;

import com.javaweb.qqzone.bean.HostReply;

/**
 * ClassName: HostReplyDAO
 * Description:
 *
 * @Author wggglggg
 * @Create 2023/7/17 12:48
 * @Version 1.0
 */
public interface HostReplyDAO {

    HostReply getHostReplyByReplyId(Integer replyId);

    void delHostReplyByReplyId(Integer replyId);
}
