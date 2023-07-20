package com.javaweb.qqzone.service.impl;

import com.javaweb.qqzone.bean.HostReply;
import com.javaweb.qqzone.dao.HostReplyDAO;
import com.javaweb.qqzone.service.HostReplyService;

/**
 * ClassName: HostReplyServiceImpl
 * Description:
 *
 * @Author wggglggg
 * @Create 2023/7/19 9:32
 * @Version 1.0
 */
public class HostReplyServiceImpl implements HostReplyService {

    private HostReplyDAO hostReplyDAO;
    @Override
    public HostReply getHostReplyByReplyId(Integer replyId) {
       return hostReplyDAO.getHostReplyByReplyId(replyId);

    }

    @Override
    public void delHostReplyByReplyId(Integer replyId) {
        hostReplyDAO.delHostReplyByReplyId(replyId);
    }
}
