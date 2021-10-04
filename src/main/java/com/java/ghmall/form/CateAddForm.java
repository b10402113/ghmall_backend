package com.java.ghmall.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class CateAddForm {

    @NotNull
    private Integer parentId;

    @NotBlank
    private String name;

    private Integer sortOrder;

}