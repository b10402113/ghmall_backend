package com.java.ghmall.form;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CartAddForm {

    @NotNull
    private Integer productId;

    @NotNull
    private Integer quantity = 1;

    private Boolean selected = true;
}
