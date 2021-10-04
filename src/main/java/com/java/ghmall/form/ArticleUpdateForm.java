package com.java.ghmall.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ArticleUpdateForm {
    @NotBlank
    private String title;

    @NotNull
    private Integer type;

    private Boolean isHot;

    @NotBlank
    private String detail;
}