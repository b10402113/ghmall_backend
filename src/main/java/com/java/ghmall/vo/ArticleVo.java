package com.java.ghmall.vo;

import lombok.Data;

import java.util.Date;

@Data
public class ArticleVo {

    private Integer id;

    private String title;

    private Integer viewCount;

    private String detail;

    private Date createTime;

    private Date updateTime;

}
