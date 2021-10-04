package com.java.ghmall.enums;

import lombok.Getter;

@Getter
public enum ResponseEnum {
    ERROR(-1,"服務器錯誤"),
    SUCCESS(0,"成功"),
    PASSWORD_ERROR(1,"密碼錯誤"),
    USERNAME_EXIST(4,"用戶已存在"),
    EMAIL_EXIST(5,"郵箱已存在"),
    USERNAME_OR_EMAIL_ERROR(6,"用戶密碼或郵箱錯誤"),
    NEED_LOGIN(10,"用戶尚未登入"),
    NO_SUCH_USER(10,"無此用戶"),
    VERIFIED_CODE_ERROR(10,"驗證碼錯誤"),
    PARAM_ERROR(3,"參數錯誤"),
    PRODUCT_OFF_SALE_OR_DELETE(12, "商品下架或删除"),
    PRODUCT_NOT_EXIST(13, "商品不存在"),
    PRODUCT_STOCK_ERROR(14, "庫存有誤"),

    CART_PRODUCT_NOT_EXIST(15, "购物车里无此商品"),

    DELETE_SHIPPING_FAIL(16, "删除收货地址失败"),

    SHIPPING_NOT_EXIST(17, "收货地址不存在"),

    CART_SELECTED_IS_EMPTY(18, "请选择商品后下单"),

    ORDER_NOT_EXIST(19, "订单不存在"),

    ORDER_STATUS_ERROR(20, "订单状态有误"),
    SHIPPING_ADD_ERROR(21, "收穫地址新增師ㄅㄞˋ"),
    PHONE_FORMAT_ERROR(22, "電話號碼格式錯誤"),
    NO_RIGHT(22, "權限不足"),
    CATEGORY_PARAM_ERROR(23,"添加品类参数错误")




    ;

    Integer code;
    String desc;


    ResponseEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
