package com.java.ghmall.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ArticleAddForm {
    @NotBlank
    private String title;

    @NotNull
    private Integer type;

    private Boolean isHot;

    private Integer viewCount;

    @NotBlank
    private String detail;
}
