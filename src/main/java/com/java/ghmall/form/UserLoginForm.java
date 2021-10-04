package com.java.ghmall.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserLoginForm {

    @NotBlank
    private String phone;

    @NotBlank
    private String password;
}
