package com.java.ghmall.service;

import com.java.ghmall.vo.ResponseVo;

public interface  ITextService {
    /**
     * 手機驗證碼寄信
     */
    ResponseVo sendVerifiedText(String phone);
    /**
     * 手機驗證碼驗證
     */
    Boolean checkVerifiedText(String phone,String code);
}
