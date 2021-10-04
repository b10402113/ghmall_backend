package com.java.ghmall.controller.backend;

import com.github.pagehelper.PageInfo;
import com.java.ghmall.consts.MallConst;
import com.java.ghmall.enums.ResponseEnum;
import com.java.ghmall.form.UserLoginForm;
import com.java.ghmall.pojo.User;
import com.java.ghmall.service.IUserService;
import com.java.ghmall.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
@RequestMapping("/manage/user")
@Slf4j
public class UserManageController {
    @Autowired
    private IUserService userService;

    @PostMapping("/login")
    public ResponseVo<User> login(@Valid @RequestBody UserLoginForm userLoginForm,
                                  HttpSession session) {
        ResponseVo<User> userResponseVo = userService.login(userLoginForm.getPhone(), userLoginForm.getPassword());
        if(userResponseVo.getData().getRole() != 1){
            return ResponseVo.error(ResponseEnum.ERROR,"您無權限");
        }
        //设置Session
        session.setAttribute(MallConst.CURRENT_USER, userResponseVo.getData());

        log.info("/login sessionId={}", session.getId());

        return userResponseVo;
    }

    @GetMapping("/")
    public ResponseVo<PageInfo> list(@RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                     @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                                     HttpSession session) {
        User user = (User) session.getAttribute(MallConst.CURRENT_USER);
        if(user.getRole() != 1){
            return ResponseVo.error(ResponseEnum.ERROR,"您無權限");
        }
        return userService.list(pageNum, pageSize);
    }
}
