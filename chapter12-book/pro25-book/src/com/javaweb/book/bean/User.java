package com.javaweb.book.bean;

import java.util.List;

/**
 * ClassName: User
 * Description:
 *
 * @Author wggglggg
 * @Create 2023/7/20 21:51
 * @Version 1.0
 */
public class User {
    private Integer id;
    private String uname;
    private String pwd;
    private String email;
    private Integer role;

    private Cart cart;
    private List<OrderBean> orderBeanList;

    public User() {
    }
    public User(Integer id) {
        this.id = id;
    }

    public User(String uname, String pwd) {
        this.uname = uname;
        this.pwd = pwd;
    }

    public User(Integer id, String uname, String pwd, String email, Integer role) {
        this.id = id;
        this.uname = uname;
        this.pwd = pwd;
        this.email = email;
        this.role = role;
    }


    public List<OrderBean> getOrderBeanList() {
        return orderBeanList;
    }

    public void setOrderBeanList(List<OrderBean> orderBeanList) {
        this.orderBeanList = orderBeanList;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", uname='" + uname + '\'' +
                ", pwd='" + pwd + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                ", cart=" + cart +
                '}';
    }
}
