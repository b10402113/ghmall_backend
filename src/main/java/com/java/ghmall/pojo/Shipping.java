package com.java.ghmall.pojo;

import lombok.Data;

import java.util.Date;
@Data
public class Shipping {
    private Integer id;

    private Integer userId;

    private String processId;

    private String stCate;

    private String stCode;

    private String stName;

    private String stAddr;

    private String stTel;

    private Integer shippingType;

    private Date createTime;

    private Date updateTime;

}