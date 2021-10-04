package com.java.ghmall.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class User {
    private Integer id;

    private String username;

    private String password;

    private String email;

    private String openid;

    private Integer verified;

    private String phone;

    private Integer referralUser;

    private String referralCode;

    private String question;

    private String answer;

    private Integer role;

    private Date createTime;

    private Date updateTime;

}