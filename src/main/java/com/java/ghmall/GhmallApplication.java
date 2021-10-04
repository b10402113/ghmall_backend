package com.java.ghmall;

import com.java.ghmall.service.impl.EmailServiceImpl;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;

@SpringBootApplication
@MapperScan("com.java.ghmall.dao")
public class GhmallApplication {

    private static final Logger LOG = LoggerFactory.getLogger(GhmallApplication.class);
    @Autowired
    private EmailServiceImpl emailService;

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(GhmallApplication.class);
        Environment env = app.run(args).getEnvironment();

        LOG.info("啟動成功！！");
        LOG.info("地址: \thttp://127.0.0.1:{}", env.getProperty("server.port"));
    }

//    @EventListener(ApplicationReadyEvent.class)
//    public void triggerWhenStarts(){
//        emailService.sendMail("andycheeehigh@gmail.com","hi,there","test");
//    }

}
