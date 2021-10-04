package com.java.ghmall.form;

import lombok.Data;

@Data
public class CateUpdateForm {
    private Integer id;

    private Integer parentId;

    private String name;

    private Boolean status;

    private Integer sortOrder;

}
