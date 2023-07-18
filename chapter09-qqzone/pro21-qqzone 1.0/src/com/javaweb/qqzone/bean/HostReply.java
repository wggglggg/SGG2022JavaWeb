package com.javaweb.qqzone.bean;

import java.sql.Date;

/**
 * ClassName: HostReply
 * Description:
 *
 * @Author wggglggg
 * @Create 2023/7/17 9:20
 * @Version 1.0
 */
public class HostReply {
    private Integer id;
    private String content;
    private Date hostReplydate;
    private UserBasic author;       //M:1
    private Reply reply;            //1:1

    public HostReply() {
    }

    public HostReply(Integer id, String content, Date hostReplydate, UserBasic author, Reply reply) {
        this.id = id;
        this.content = content;
        this.hostReplydate = hostReplydate;
        this.author = author;
        this.reply = reply;
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

    public Date getHostReplydate() {
        return hostReplydate;
    }

    public void setHostReplydate(Date hostReplydate) {
        this.hostReplydate = hostReplydate;
    }

    public UserBasic getAuthor() {
        return author;
    }

    public void setAuthor(UserBasic author) {
        this.author = author;
    }

    public Reply getReply() {
        return reply;
    }

    public void setReply(Reply reply) {
        this.reply = reply;
    }

    @Override
    public String toString() {
        return "HostReply{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", hostReplydate=" + hostReplydate +
                ", author=" + author +
                ", reply=" + reply +
                '}';
    }
}
