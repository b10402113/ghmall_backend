package com.java.ghmall.service.impl;

import com.java.ghmall.GhmallApplicationTests;
import com.java.ghmall.service.ITextService;
import com.java.ghmall.service.IUserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class TextServiceImplTest extends GhmallApplicationTests {
    @Autowired
    private ITextService textService;
    public static final String PHONE = "0912345678";

    @Test
    public void sendText() {
        textService.sendVerifiedText(PHONE);
    }

    @Test
    public void verifiedCode() {
        textService.checkVerifiedText(PHONE,"671860");
    }
}
