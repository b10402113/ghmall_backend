package com.java.ghmall.enums;

import lombok.Getter;

@Getter
public enum ProductTagEnum {

    NORMAL(0),

    PROMOTION(1),

    HOT(2),



    ;

    Integer code;

    ProductTagEnum(Integer code) {
        this.code = code;
    }
}
