package com.java.ghmall.service.impl;

import com.java.ghmall.GhmallApplicationTests;
import com.java.ghmall.enums.ResponseEnum;
import com.java.ghmall.form.ShippingForm;
import com.java.ghmall.service.IShippingService;
import com.java.ghmall.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

import static org.junit.Assert.*;
@Slf4j
public class ShippingServiceImplTest extends GhmallApplicationTests {

    @Autowired
    private IShippingService shippingService;

    private Integer uid = 2;

    private ShippingForm form;

    private Integer shippingId;

    @Before
    public void before() {
        ShippingForm form = new ShippingForm();
//        form.setProcessId("1");
//        form.setShippingType(0);
//        form.setStCate("TFM");
//        form.setStCode("14482");
//        form.setStName("全家通昌店");
        form.setStAddr("台北市大安區文昌街142號壹樓全部");
        this.form = form;

        add();
    }

    public void add() {
        ResponseVo<Map<String, Integer>> responseVo = shippingService.add(uid, form,1);
        Assert.assertEquals(ResponseEnum.SUCCESS.getCode(), responseVo.getStatus());
        log.info("result={}", responseVo);
        log.info("shippingId={}",responseVo.getData().get("shippingId"));
        this.shippingId = responseVo.getData().get("shippingId");
//        Assert.assertEquals(ResponseEnum.SUCCESS.getCode(), responseVo.getStatus());
    }

//    @After
    public void delete() {
        log.info(String.valueOf(shippingId));
        ResponseVo responseVo = shippingService.delete(uid, shippingId);
        log.info("result={}", responseVo);
        Assert.assertEquals(ResponseEnum.SUCCESS.getCode(), responseVo.getStatus());
    }

    @Test
    public void update() {
        form.setStAddr("杭州");
        ResponseVo responseVo = shippingService.update(uid, shippingId, form);
        log.info("result={}", responseVo);
        Assert.assertEquals(ResponseEnum.SUCCESS.getCode(), responseVo.getStatus());
    }

    @Test
    public void list() {
        ResponseVo responseVo = shippingService.list(uid, 1, 10);
        log.info("result={}", responseVo);
        Assert.assertEquals(ResponseEnum.SUCCESS.getCode(), responseVo.getStatus());
    }
}