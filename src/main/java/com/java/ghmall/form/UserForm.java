package com.java.ghmall.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Data
public class UserForm {

//    @NotBlank 用於字符
//    @NotEmpty 用於集合
    @NotBlank
    private String email;

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @NotBlank
    private String phone;

    @NotBlank
    private String verifiedCode;


    private String ref;



}
