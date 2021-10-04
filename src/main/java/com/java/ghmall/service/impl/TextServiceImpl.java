package com.java.ghmall.service.impl;

import com.google.gson.Gson;
import com.java.ghmall.consts.MallConst;
import com.java.ghmall.dao.UserMapper;
import com.java.ghmall.enums.ResponseEnum;
import com.java.ghmall.form.TaiwanTextResponseForm;
import com.java.ghmall.pojo.User;
import com.java.ghmall.service.ITextService;
import com.java.ghmall.utils.ValidateUtils;
import com.java.ghmall.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.TimeUnit;



@Service
@Slf4j
public class TextServiceImpl implements ITextService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    private static int phoneCodeExpires = 12000;

    private static int phoneVerifiedExpires = 1200;

    private static String phoneCodeFormat = "phone_code_%s";

    private static String phoneVerifiedFormat = "phone_verified_%s";

    private static String successVerified = "successVerified";

    private Gson gson = new Gson();

    @Autowired
    private UserMapper userMapper;

    @Override
    public ResponseVo sendVerifiedText(String phone) {
//        校驗電話號碼
        if(!ValidateUtils.validPhoneNum(phone)){
            return ResponseVo.error(ResponseEnum.PHONE_FORMAT_ERROR);
        }
//        校驗資料庫
        User user = userMapper.selectByPhone(phone);
        if(user != null){
            System.out.println("用戶已存在");
            return ResponseVo.error(ResponseEnum.USERNAME_EXIST);
        }

        String key = String.format(phoneCodeFormat, phone);
        Boolean hasCode = redisTemplate.hasKey(key);
        if(hasCode){
            return ResponseVo.error(ResponseEnum.ERROR,"已經寄出信件，60秒後重試");
        }
//        redis中沒有code值

        RestTemplate restTemplate = new RestTemplate();
        String code = smsCode();
        String smsContent = "【魯爾德國健康保健】簡訊驗證碼："+code+"，如非本人操作，請忽略此短信。";
        String response = restTemplate.getForObject(MallConst.twtUrl, String.class, phone,smsContent);
        TaiwanTextResponseForm resultForm  = gson.fromJson(response, TaiwanTextResponseForm.class);
//        驗證回傳訊息
        if(StringUtils.equals(resultForm.getCode(), "00000")){
            redisTemplate.opsForValue().set(key, code, phoneCodeExpires, TimeUnit.SECONDS);
            return ResponseVo.successByMsg("簡訊發送成功");
        }else {
            System.out.println(resultForm.getCode());
            System.out.println(code);
            System.out.println(key);
            redisTemplate.opsForValue().set(key, code, phoneCodeExpires, TimeUnit.SECONDS);
            return ResponseVo.error(ResponseEnum.ERROR, resultForm.getText());
        }
    }

    @Override
    public Boolean checkVerifiedText(String phone, String code) {
        String realCode = redisTemplate.opsForValue().get(String.format(phoneCodeFormat, phone));
        System.out.println(realCode);
        boolean isVerified = StringUtils.equals(code, realCode);
        if(isVerified){
//            存入Redis中
            String key = String.format(phoneVerifiedFormat,phone);
            redisTemplate.opsForValue().set(key,successVerified,phoneVerifiedExpires,TimeUnit.SECONDS);
            return true;
        }else{
            System.out.println("驗證失敗");
            return false;
        }

    }

    public static String smsCode(){
        String random=(int)((Math.random()*9+1)*100000)+"";
        System.out.println("短信驗證碼："+random);

        return random;
    }

}
