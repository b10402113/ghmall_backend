package com.java.ghmall.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ShippingForm {
    @NotBlank
    private String processID;

    private String stCate;

    private String stCode;

    private String stName;

    @NotBlank
    private String stAddr;

    private String stTel;

    @NotBlank
    private String webPara;

//    @NotBlank
//    private Integer shippingType;
}
