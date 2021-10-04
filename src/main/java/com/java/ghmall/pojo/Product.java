package com.java.ghmall.pojo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class Product {
    private Integer id;

    private Integer categoryId;

    private String name;

    private String subtitle;

    private String mainImage;

    private BigDecimal price;

    private Integer originPrice;

    private Integer stock;

    private Integer status;

    private Date createTime;

    private Date updateTime;

    private Integer isHot;

    private Integer viewCount;

    private Integer tag;

    private String subImages;

    private String detail;

}