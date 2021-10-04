package com.java.ghmall.form;


import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.math.BigInteger;

@Data
public class TaiwanTextResponseForm {

    private String code;


    private String text;


    private BigInteger msgid;


}
