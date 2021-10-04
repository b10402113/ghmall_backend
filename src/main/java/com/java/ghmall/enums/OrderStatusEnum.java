package com.java.ghmall.enums;

import lombok.Getter;

/**
 * 订单状态:0-已取消-10-待確認，20-已確認，40-已發貨，50-交易成功，60-交易关闭
 * Created by 廖师兄
 */
@Getter
public enum OrderStatusEnum {

	CANCELED(0, "已取消"),

	WAIT_CONFIRM(10, "待確認"),

	CONFIRMED(20, "已確認"),

	SHIPPED(40, "已發貨"),

	TRADE_SUCCESS(50, "交易成功"),

	TRADE_CLOSE(60, "交易关闭"),
	;

	Integer code;

	String desc;

	OrderStatusEnum(Integer code, String desc) {
		this.code = code;
		this.desc = desc;
	}
}
