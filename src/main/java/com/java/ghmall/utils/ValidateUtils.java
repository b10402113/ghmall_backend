package com.java.ghmall.utils;

import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateUtils {


    public static void main(String[] args) throws ParseException {
        //已测试通过

        System.out.println(validPhoneNum("0975518432"));
    }
    public static boolean validPhoneNum(String phone){

        String PHONE= "^[0][9]\\d{8}$";//校验规则
        //长度判断
        if(phone.length()==10){
            Pattern p = Pattern.compile(PHONE);
            Matcher m = p.matcher(phone);
            if(m.matches()==false){
                return false;
            }
            return true;
        }
        return false;

    }
}
