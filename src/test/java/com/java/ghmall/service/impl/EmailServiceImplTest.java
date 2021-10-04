package com.java.ghmall.service.impl;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class EmailServiceImplTest {

    @Autowired
    private EmailServiceImpl emailService;

    @Autowired
    private JavaMailSender mailSender;

    @Test
    public void sendSimpleMail() throws Exception {
         emailService.sendMail("andyceeehigh@gmail.com","主旨：Hello J.J.Huang","內容：這是一封測試信件，恭喜您成功發送了唷");
    }
}