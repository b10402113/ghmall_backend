package com.java.ghmall.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class Order {
    private Integer id;

    private Long orderNo;

    private Integer userId;

    private String receiverName;

    private String receiverPhone;

    private String receiverEmail;

    private Integer shippingId;

    private Integer payment;

    private Integer paymentType;

    private Integer postage;

    private Integer status;

    private Date paymentTime;

    private Date sendTime;

    private Date endTime;

    private Date closeTime;

    private Date createTime;

    private Date updateTime;

}