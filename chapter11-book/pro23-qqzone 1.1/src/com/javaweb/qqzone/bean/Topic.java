package com.javaweb.qqzone.bean;

import java.sql.Date;

import java.sql.Timestamp;
import java.util.List;

/**
 * ClassName: Topic
 * Description:
 *
 * @Author wggglggg
 * @Create 2023/7/17 9:19
 * @Version 1.0
 */
public class Topic {
    private Integer id;
    private String title;
    private String content;
    private Date topicDate;
    private UserBasic author;

    private List<Reply> replyList;

    public Topic() {
    }

    public Topic(Integer id) {
        this.id = id;
    }

    public Topic(String title, String content, Date topicDate, UserBasic author) {
        this.title = title;
        this.content = content;
        this.topicDate = topicDate;
        this.author = author;
    }

    public Topic(Integer id, String title, String content, Date topicDate, UserBasic author, List<Reply> replyList) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.topicDate = topicDate;
        this.author = author;
        this.replyList = replyList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getTopicDate() {
        return topicDate;
    }

    public void setTopicDate(Date topicDate) {
        this.topicDate = topicDate;
    }

    public UserBasic getAuthor() {
        return author;
    }

    public void setAuthor(UserBasic author) {
        this.author = author;
    }

    public List<Reply> getReplyList() {
        return replyList;
    }

    public void setReplyList(List<Reply> replyList) {
        this.replyList = replyList;
    }

    @Override
    public String toString() {
        return "Topic{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", topicDate=" + topicDate +
                ", author=" + author +
                ", replyList=" + replyList +
                '}';
    }
}
