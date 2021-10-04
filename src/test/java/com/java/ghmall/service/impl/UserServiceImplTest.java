package com.java.ghmall.service.impl;

import com.java.ghmall.GhmallApplicationTests;
import com.java.ghmall.dao.UserMapper;
import com.java.ghmall.enums.ResponseEnum;
import com.java.ghmall.form.UserUpdateForm;
import com.java.ghmall.pojo.User;
import com.java.ghmall.service.IUserService;
//import org.junit.jupiter.api.Test;
import com.java.ghmall.vo.ResponseVo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import javax.validation.constraints.Email;

//@Transactional
public class UserServiceImplTest extends GhmallApplicationTests {

    @Autowired
    private IUserService userService;

    @Autowired
    private UserMapper userMapper;

    public static final String USERNAME = "andy";

    public static final String PASSWORD = "a7935776";
    public static final String EMAIL = "andycheeehigh@gmail.com";
    public static final String VERIFIEDCODE = "123456";
    public static final Integer UID = 30;

//
//    @Test
//    public void register() {
//        User user = new User(USERNAME,PASSWORD, EMAIL,0);
//        ResponseVo<User> result = userService.register(user);
//        Assert.assertEquals(ResponseEnum.SUCCESS.getCode(), result.getStatus());
//    }
//
//    @Test
//    public void login() {
//        ResponseVo<User> user = userService.login(EMAIL, PASSWORD);
//        Assert.assertEquals(ResponseEnum.SUCCESS.getCode(), user.getStatus());
//    }
//
//    @Test
//    public void checkVerifiedCode() {
//        ResponseVo result = userService.checkVerifiedCode(EMAIL,VERIFIEDCODE);
//        Assert.assertEquals(ResponseEnum.SUCCESS.getCode(), result.getStatus());
//    }
//
//    @Test
//    public void update() {
//        UserUpdateForm form = new UserUpdateForm("andy7788","a7935776");
//        User user = userMapper.selectByPrimaryKey(UID);
//        ResponseVo result = userService.update(user,form);
//        Assert.assertEquals(ResponseEnum.SUCCESS.getCode(), result.getStatus());
//    }


}