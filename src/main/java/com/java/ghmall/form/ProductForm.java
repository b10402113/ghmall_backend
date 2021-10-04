package com.java.ghmall.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class ProductForm {
    private Integer id;

    @NotNull
    private Integer categoryId;

    @NotEmpty
    private String name;

    private String subtitle;

    private String mainImage;

    private String subImages;

    @NotNull
    private BigDecimal price;

    private Integer originPrice;

    private Integer stock;

    private Integer status;

    private String detail;
    private Integer tag;
}
