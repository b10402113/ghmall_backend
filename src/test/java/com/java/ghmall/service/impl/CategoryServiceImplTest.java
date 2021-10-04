package com.java.ghmall.service.impl;

import com.java.ghmall.GhmallApplicationTests;
import com.java.ghmall.enums.ResponseEnum;
import com.java.ghmall.service.ICategoryService;
import com.java.ghmall.vo.ResponseVo;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class CategoryServiceImplTest extends GhmallApplicationTests {
    @Autowired
    private ICategoryService categoryService;

    @Test
    void selectAll() {
        ResponseVo result = categoryService.selectAll();
        Assert.assertEquals(ResponseEnum.SUCCESS.getCode(), result.getStatus());
    }
}