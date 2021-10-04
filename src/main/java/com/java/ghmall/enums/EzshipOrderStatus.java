package com.java.ghmall.enums;

public enum EzshipOrderStatus {
    S01(0,"S01","訂單新增成功"),
    E00(1,"E00"," 參數傳遞內容有誤或欄位短缺"),
    E01(2,"E01","<su_id>帳號不存在"),
    E02(3,"E02","<su_id>帳號無建立取貨付款權限 或 無網站串接權限 或 無ezShip宅配權限"),
    E03(4,"E03","<su_id>帳號無可用之 輕鬆袋 或 迷你袋"),
    E04(5,"E04","<st_code>取件門市有誤 "),
    E05(6,"E05","<order_amount>金額有誤 "),
    E06(7,"E06"," <rv_email>格式有誤 "),
    E07(8,"E07"," <rv_mobile>格式有誤"),
    E08(9,"E08","<order_status>內容有誤 或 為空值 "),
    E09(10,"E09"," <order_type>內容有誤 或 為空值 "),
    E10(11,"E10","<rv_name>內容有誤 或 為空值"),
    E11(12,"E11","<rv_addr>內容有誤 或 為空值 "),
    E98(20,"E98","系統發生錯誤無法載入 "),
    E99(21,"E99","系統錯誤"),
    ;

    Integer code;
    String orderStatus;
    String desc;


    EzshipOrderStatus(Integer code,String orderStatus,String desc) {
        this.desc = desc;
        this.orderStatus = orderStatus;
        this.code = code;
    }
    public String getOrderStatus() {
        return this.orderStatus;
    }
    public String getDesc() {
        return this.desc;
    }
}

