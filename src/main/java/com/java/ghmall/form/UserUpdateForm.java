package com.java.ghmall.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserUpdateForm {

    @NotBlank
    private String username;


//    public UserUpdateForm(@NotBlank String username) {
//        this.username = username;
//    }
}
