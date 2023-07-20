package com.javaweb.qqzone.bean;

import java.sql.Date;

/**
 * ClassName: UserDetail
 * Description:
 *
 * @Author wggglggg
 * @Create 2023/7/17 9:35
 * @Version 1.0
 */
public class UserDetail {
    private Integer id;
    private String realName;
    private String tel;
    private String email;
    private Date birth;
    private String star;

    public UserDetail() {
    }

    public UserDetail(Integer id, String realName, String tel, String email, Date birth, String star) {
        this.id = id;
        this.realName = realName;
        this.tel = tel;
        this.email = email;
        this.birth = birth;
        this.star = star;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    @Override
    public String toString() {
        return "UserDetail{" +
                "id=" + id +
                ", realName='" + realName + '\'' +
                ", tel='" + tel + '\'' +
                ", email='" + email + '\'' +
                ", birth=" + birth +
                ", star='" + star + '\'' +
                '}';
    }
}
