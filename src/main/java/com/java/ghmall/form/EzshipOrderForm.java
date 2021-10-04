package com.java.ghmall.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class EzshipOrderForm {
    @NotBlank
    private Long order_id;

    @NotNull
    private Integer order_amount;

    @NotBlank
    private String rv_name;

    @NotBlank
    private String rv_email;

    @NotBlank
    private String rv_mobile;

    @NotBlank
    private String st_code;

    private String rtn_url;

}
