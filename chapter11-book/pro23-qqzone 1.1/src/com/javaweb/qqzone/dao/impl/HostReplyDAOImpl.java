package com.javaweb.qqzone.dao.impl;

import com.javaweb.qqzone.bean.HostReply;
import com.javaweb.qqzone.dao.HostReplyDAO;
import com.javaweb.base.BaseDAO;

/**
 * ClassName: HostReplyDAOImpl
 * Description:
 *
 * @Author wggglggg
 * @Create 2023/7/19 9:26
 * @Version 1.0
 */
public class HostReplyDAOImpl extends BaseDAO<HostReply> implements HostReplyDAO {
    @Override
    public HostReply getHostReplyByReplyId(Integer replyId) {
        String sql = "select * from t_host_reply where reply = ?";
        return load(sql, replyId);
    }

    @Override
    public void delHostReplyByReplyId(Integer replyId) {
        String sql = "delete from t_host_reply where reply = ?";
        executeUpdate(sql, replyId);
    }

}
