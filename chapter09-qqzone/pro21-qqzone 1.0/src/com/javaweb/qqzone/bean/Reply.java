package com.javaweb.qqzone.bean;

import java.sql.Date;

/**
 * ClassName: Reply
 * Description:
 *
 * @Author wggglggg
 * @Create 2023/7/17 9:20
 * @Version 1.0
 */
public class Reply {
    private Integer id;
    private String  content;
    private Date replyDate;

    private UserBasic author;       //M:1
    private Topic topic;            //M:1

    private HostReply hostReply;    //1:1

    public Reply() {
    }

    public Reply(Integer id, String content, Date replyDate, UserBasic author, Topic topic, HostReply hostReply) {
        this.id = id;
        this.content = content;
        this.replyDate = replyDate;
        this.author = author;
        this.topic = topic;
        this.hostReply = hostReply;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getReplyDate() {
        return replyDate;
    }

    public void setReplyDate(Date replyDate) {
        this.replyDate = replyDate;
    }

    public UserBasic getAuthor() {
        return author;
    }

    public void setAuthor(UserBasic author) {
        this.author = author;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public HostReply getHostReply() {
        return hostReply;
    }

    public void setHostReply(HostReply hostReply) {
        this.hostReply = hostReply;
    }

    @Override
    public String toString() {
        return "Reply{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", replyDate=" + replyDate +
                ", author=" + author +
                ", topic=" + topic +
                ", hostReply=" + hostReply +
                '}';
    }
}
