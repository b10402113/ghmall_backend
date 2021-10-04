package com.java.ghmall.service.impl;

import com.github.pagehelper.PageInfo;
import com.java.ghmall.GhmallApplicationTests;
import com.java.ghmall.enums.ResponseEnum;
import com.java.ghmall.service.IProductService;
import com.java.ghmall.vo.ProductDetailVo;
import com.java.ghmall.vo.ResponseVo;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class ProductServiceImplTest extends GhmallApplicationTests {

    @Autowired
    private IProductService productService;

    @Test
    public void list() {
        ResponseVo<PageInfo> responseVo = productService.list(null, 2, 3);
        Assert.assertEquals(ResponseEnum.SUCCESS.getCode(), responseVo.getStatus());
    }
    @Test
    public void detail() {
        ResponseVo<ProductDetailVo> responseVo = productService.detail(26);
        Assert.assertEquals(ResponseEnum.SUCCESS.getCode(), responseVo.getStatus());
    }
}