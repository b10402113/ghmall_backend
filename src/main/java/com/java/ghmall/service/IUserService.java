package com.java.ghmall.service;

import com.github.pagehelper.PageInfo;
import com.java.ghmall.form.UserUpdateForm;
import com.java.ghmall.pojo.User;
import com.java.ghmall.vo.ResponseVo;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

public interface IUserService {
    /**
     * 註冊
     */
    ResponseVo<User> register(User user);
    /**
     * 登入
     */
    ResponseVo<User> login(String email, String password);
    /**
     * 驗證密碼
     */
    ResponseVo checkVerifiedCode(String email, String verifiedCode);
    /**
     * 更新用戶
     */
    ResponseVo<User> update(User user, @Valid UserUpdateForm updateForm);

    ResponseVo checkAdminRole(User user);

    Integer getReferrerUser(String code);

    ResponseVo<PageInfo> list(Integer pageNum, Integer pageSize);
}
