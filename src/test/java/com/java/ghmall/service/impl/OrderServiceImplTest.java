package com.java.ghmall.service.impl;

import com.google.gson.GsonBuilder;
import com.java.ghmall.GhmallApplicationTests;
import com.java.ghmall.enums.ResponseEnum;
import com.java.ghmall.form.CartAddForm;
import com.java.ghmall.service.ICartService;
import com.java.ghmall.service.IOrderService;
import com.java.ghmall.service.IProductService;
import com.java.ghmall.vo.CartVo;
import com.java.ghmall.vo.OrderVo;
import com.java.ghmall.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;
import com.google.gson.Gson;
@Slf4j
public class OrderServiceImplTest extends GhmallApplicationTests {
    @Autowired
    private IOrderService orderService;

    @Autowired
    private ICartService cartService;

    private Integer uid = 1;
    private Integer shippingId=29;
    private Integer productId = 26;
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Before
    public void before() {
        CartAddForm form = new CartAddForm();
        form.setProductId(productId);
        form.setSelected(true);
        form.setQuantity(3);
        ResponseVo<CartVo> responseVo = cartService.add(uid, form);
        Assert.assertEquals(ResponseEnum.SUCCESS.getCode(), responseVo.getStatus());
    }

    @Test
    public void create() {
        ResponseVo result = orderService.create(uid,"鄭","0975518436","bsj@gmail.com",shippingId);
        Assert.assertEquals(ResponseEnum.SUCCESS.getCode(), result.getStatus());
    }

    @Test
    public void list() {
        ResponseVo result = orderService.list(uid,1,10);
    }

    @Test
    public void detail() {
        ResponseVo<OrderVo> vo = createVo();
        long orderId = 12423523523L;
        ResponseVo<OrderVo> responseVo = orderService.detail(uid,orderId );
        log.info("result={}", gson.toJson(responseVo));
        Assert.assertEquals(ResponseEnum.SUCCESS.getCode(), responseVo.getStatus());
    }

    private ResponseVo<OrderVo> createVo() {
        ResponseVo<OrderVo> responseVo = orderService.create(uid,"鄭","0975518436","gmail.com", shippingId);
        log.info("result={}", gson.toJson(responseVo));
        return responseVo;
    }

    @Test
    public void cancel() {
        ResponseVo<OrderVo> vo = createVo();
        ResponseVo responseVo = orderService.cancel(uid, vo.getData().getOrderNo());
        log.info("result={}", gson.toJson(responseVo));
        Assert.assertEquals(ResponseEnum.SUCCESS.getCode(), responseVo.getStatus());
    }
}