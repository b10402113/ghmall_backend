package com.java.ghmall.vo;

import lombok.Data;

import java.util.List;

@Data
public class CategoryVo {
    private Integer id;

    private Integer parentId;

    private String name;

    private Integer sortOrder;

    private Boolean status;

    private List<CategoryVo> subCategories;
}
