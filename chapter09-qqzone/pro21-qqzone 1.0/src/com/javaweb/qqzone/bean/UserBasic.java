package com.javaweb.qqzone.bean;


import java.util.List;

/**
 * ClassName: UserBasic
 * Description:
 *
 * @Author wggglggg
 * @Create 2023/7/17 9:19
 * @Version 1.0
 */
public class UserBasic {
    private Integer id;
    private String loginId;
    private String nickName;
    private String pwd;
    private String headImg;

    private UserDetail userDetail;   //1:1  用户A：用户A的个人说明
    private List<Topic> topicList;  //1:N   用户A：用户A的多个贴子
    private List<UserBasic> friendList; //M:N 用户A：用户A的朋友们， 同时这些朋友们的朋友清单中也有：用户A

    public UserBasic() {
    }

    public UserBasic(Integer id) {
        this.id = id;
    }

    public UserBasic(Integer id, String loginId, String nickName, String pwd, String headImg, UserDetail userDetail, List<Topic> topicList, List<UserBasic> friendList) {
        this.id = id;
        this.loginId = loginId;
        this.nickName = nickName;
        this.pwd = pwd;
        this.headImg = headImg;
        this.userDetail = userDetail;
        this.topicList = topicList;
        this.friendList = friendList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getNickname() {
        return nickName;
    }

    public void setNickname(String nickname) {
        this.nickName = nickname;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public UserDetail getUserDetail() {
        return userDetail;
    }

    public void setUserDetail(UserDetail userDetail) {
        this.userDetail = userDetail;
    }

    public List<Topic> getTopicList() {
        return topicList;
    }

    public void setTopicList(List<Topic> topicList) {
        this.topicList = topicList;
    }

    public List<UserBasic> getFriendList() {
        return friendList;
    }

    public void setFriendList(List<UserBasic> friendList) {
        this.friendList = friendList;
    }

    @Override
    public String toString() {
        return "UserBasic{" +
                "id=" + id +
                ", loginId='" + loginId + '\'' +
                ", nickname='" + nickName + '\'' +
                ", pwd='" + pwd + '\'' +
                ", headImg='" + headImg + '\'' +
                ", userDetail=" + userDetail +
                ", topicList=" + topicList +
                ", friendList=" + friendList +
                '}';
    }
}
