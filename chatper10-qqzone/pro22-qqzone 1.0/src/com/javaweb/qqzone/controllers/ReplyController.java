package com.javaweb.qqzone.controllers;

import com.javaweb.qqzone.bean.Reply;
import com.javaweb.qqzone.bean.Topic;
import com.javaweb.qqzone.bean.UserBasic;
import com.javaweb.qqzone.service.HostReplyService;
import com.javaweb.qqzone.service.ReplyService;

import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;


/**
 * ClassName: ReplyController
 * Description:
 *
 * @Author wggglggg
 * @Create 2023/7/19 15:32
 * @Version 1.0
 */
public class ReplyController {

    private ReplyService replyService = null;


    public String addReply(String content, Integer topicId, HttpSession session) throws ParseException {
        UserBasic author = (UserBasic) session.getAttribute("userBasic");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date parse = sdf.parse("2023-7-19");

        long l = System.currentTimeMillis();
        System.out.println("l = " + l);


        Reply reply = new Reply(content, new Date(l), author, new Topic(topicId));

        replyService.addReply(reply);

        return "redirect:topic.do?operate=topicDetail&id=" + topicId;
    }

    public String delReply(Integer replyId, Integer topicId){
        replyService.delReply(replyId);



        return "redirect:topic.do?operate=topicDetail&id=" + topicId;
    }

}
