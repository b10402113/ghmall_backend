package com.java.ghmall.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class Article {
    private Integer id;

    private String title;

    private Integer type;

    private Boolean isHot;

    private Integer viewCount;

    private Date createTime;

    private Date updateTime;

    private String detail;

}