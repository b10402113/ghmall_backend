package com.java.ghmall.controller.portal;


import com.java.ghmall.consts.MallConst;
import com.java.ghmall.enums.ResponseEnum;
import com.java.ghmall.form.UserForm;
import com.java.ghmall.form.UserLoginForm;
import com.java.ghmall.form.UserUpdateForm;
import com.java.ghmall.pojo.User;
import com.java.ghmall.service.ITextService;
import com.java.ghmall.service.IUserService;

import com.java.ghmall.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private ITextService textService;

    @PostMapping("/phoneVerified")
    public ResponseVo<User> phoneVerified(@RequestParam(required = true) String phone){
        System.out.println(phone);
//        return null;
        return textService.sendVerifiedText(phone);
    }

//    /user/referral?id=
    @GetMapping("/referral")
    public ResponseVo setCookie(HttpServletResponse response,@RequestParam String referralCode) {
        // 创建一个 cookie对象
        if(StringUtils.isNotEmpty(referralCode)){
            Cookie cookie = new Cookie("referralCode", referralCode);
            cookie.setMaxAge(30 * 24 * 60 * 60);
            //将cookie对象加入response响应
            response.addCookie(cookie);
        }
        return ResponseVo.success("設置cookie成功");

    }



    @PostMapping("/register")
//    json的方式
    public ResponseVo<User> register(@Valid @RequestBody UserForm userForm,HttpSession session){
        Boolean isVerified = textService.checkVerifiedText(userForm.getPhone(), userForm.getVerifiedCode());
        if(!isVerified){
            return ResponseVo.error(ResponseEnum.ERROR,"認證簡訊失敗");
        }

        User user = new User();
        BeanUtils.copyProperties(userForm,user);
        // 檢查是否傳送ref參數
        if(StringUtils.isBlank(userForm.getRef())){
            // no ref user
            user.setReferralUser(0);
        }else{
            // ref user exist
            Integer referrerUser = userService.getReferrerUser(userForm.getRef());
            user.setReferralUser(referrerUser);
        }
        System.out.println(user.toString());

        return userService.register(user);

    }

    @PostMapping("/login")
    public ResponseVo<User> login(@Valid @RequestBody UserLoginForm userLoginForm,
                                  HttpSession session) {
        ResponseVo<User> userResponseVo = userService.login(userLoginForm.getPhone(), userLoginForm.getPassword());

        //设置Session
        session.setAttribute(MallConst.CURRENT_USER, userResponseVo.getData());
        log.info("/login sessionId={}", session.getId());

        return userResponseVo;
    }

    @GetMapping("/checkValid")
    public ResponseVo<User> checkValid(@RequestParam(required = true) String email,@RequestParam(required = true) String code) {
        log.info("/check Valid email={}", email);

        return userService.checkVerifiedCode(email, code);
    }
    //session保存在内存里，改进版：token+redis
    @GetMapping("/")
    public ResponseVo<User> userInfo(HttpSession session) {
        log.info("/user sessionId={}", session.getId());
        User user = (User) session.getAttribute(MallConst.CURRENT_USER);
        return ResponseVo.success(user);
    }


    @PostMapping("/logout")
    public ResponseVo logout(HttpSession session) {
        log.info("/user/logout sessionId={}", session.getId());
        session.removeAttribute(MallConst.CURRENT_USER);
        return ResponseVo.success();
    }

    @PutMapping("/")
    public ResponseVo<User> update(@Valid @RequestBody UserUpdateForm updateForm, HttpSession session){
        log.info("/user/update sessionId={}", session.getId());
        User user = (User) session.getAttribute(MallConst.CURRENT_USER);
        return userService.update(user,updateForm);
    }


}
