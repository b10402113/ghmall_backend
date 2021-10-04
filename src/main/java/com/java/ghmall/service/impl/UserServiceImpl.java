package com.java.ghmall.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.java.ghmall.common.Const;
import com.java.ghmall.common.ServerResponse;
import com.java.ghmall.consts.MallConst;
import com.java.ghmall.dao.UserMapper;
import com.java.ghmall.enums.ResponseEnum;
import com.java.ghmall.form.TaiwanTextResponseForm;
import com.java.ghmall.form.UserUpdateForm;
import com.java.ghmall.pojo.*;
import com.java.ghmall.service.IEmailService;
import com.java.ghmall.service.IUserService;
import com.java.ghmall.utils.ValidateUtils;
import com.java.ghmall.vo.OrderVo;
import com.java.ghmall.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpSession;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private IEmailService emailService;

    @Override
    public ResponseVo<User> register(User user) {
        // 校驗用戶名、信箱
        user.setRole(0);
        user.setVerified(1);
        user.setReferralCode("0");
        // countByPhone
        int countByEmail = userMapper.countByEmail(user.getEmail());
        if(countByEmail > 0){
            return ResponseVo.error(ResponseEnum.EMAIL_EXIST);
        }
        // MD5加密 Spring自帶
       user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes(StandardCharsets.UTF_8)));
        int resultCount = userMapper.insertSelective(user);
        if(resultCount == 0){
            return ResponseVo.error(ResponseEnum.ERROR);
        }
//        emailService.sendMail(user.getEmail(),"驗證電子郵件","您的驗證碼為：123456");
        user.setPassword(null);
        return ResponseVo.success(user);


    }

    @Override
    public ResponseVo<User> login(String phone, String password) {
        User user = userMapper.selectByPhone(phone);


        if (user == null) {
            //用户不存在（返回：用户名或密码错误 ）
            return ResponseVo.error(ResponseEnum.USERNAME_OR_EMAIL_ERROR);
        }

        if (!user.getPassword().equalsIgnoreCase(
                DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8)))) {
            //密码错误(返回：用户名或密码错误 )
            return ResponseVo.error(ResponseEnum.USERNAME_OR_EMAIL_ERROR);
        }

        user.setPassword(null);
        return ResponseVo.success(user);
    }

    @Override
    public ResponseVo checkVerifiedCode(String email,String verifiedCode) {
//        log.info("/user/checkVerifiedCode sessionId={}", session.getId());
//        User user = (User) session.getAttribute(MallConst.CURRENT_USER);

//        User user = userMapper.selectByPrimaryKey(uid);
        User user = userMapper.selectByEmail(email);
        if (user == null) {
            //用户不存在（返回：用户名或密码错误 ）
            return ResponseVo.error(ResponseEnum.NO_SUCH_USER);
        }
        if(!user.getVerified().equals(verifiedCode)){
            return ResponseVo.error(ResponseEnum.VERIFIED_CODE_ERROR);
        }
        user.setVerified(1);
        userMapper.updateByPrimaryKeySelective(user);
        return ResponseVo.success();
    }

    @Override
    public ResponseVo<User> update(User user, UserUpdateForm updateForm) {
//        updateForm.setPassword(DigestUtils.md5DigestAsHex(updateForm.getPassword().getBytes(StandardCharsets.UTF_8)));
        BeanUtils.copyProperties(updateForm, user);
        int result = userMapper.updateByPrimaryKeySelective(user);
        if(result == 0){
            return ResponseVo.error(ResponseEnum.ERROR);
        }
        user.setPassword(null);
        return ResponseVo.success(user);
    }

    public ResponseVo checkAdminRole(User user){
        log.info("檢查用戶");
        log.info(String.valueOf(user.getRole()));
        if(user != null && user.getRole().intValue() == MallConst.ROLE_ADMIN){
            return ResponseVo.success();
        }
        return ResponseVo.error(ResponseEnum.NO_RIGHT,"權限不足");
    }

    @Override
    public Integer getReferrerUser(String code){
        Integer id = userMapper.selectByReferralUser(code);
        if (id == null){
            return 0;
        }
        return id;
    }


    @Override
    public ResponseVo<PageInfo> list(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        List<User> userList = userMapper.selectAll();

        PageInfo pageInfo = new PageInfo<>(userList);
        pageInfo.setList(userList);

        return ResponseVo.success(pageInfo);
    }

}

