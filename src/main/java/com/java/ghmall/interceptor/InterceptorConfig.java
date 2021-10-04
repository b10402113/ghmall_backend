package com.java.ghmall.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by 廖师兄
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new UserLoginInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/images/**","/user/phoneVerified","/user/referral","/error","/orders/ezship","/carts", "/user/login", "/user/register", "/category/**", "/categories", "/products", "/products/*","/shipping");
    }
}
