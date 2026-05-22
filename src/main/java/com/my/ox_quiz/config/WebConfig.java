package com.my.ox_quiz.config;

import com.my.ox_quiz.interceptor.AdminInterceptor;
import com.my.ox_quiz.interceptor.ApprovedInterceptor;
import com.my.ox_quiz.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/member/my-page",
                        "/quiz/**",
                        "/admin/**");

        registry.addInterceptor(new AdminInterceptor())
                .addPathPatterns("/quiz",
                        "/quiz/insert", "/quiz/update", "/quiz/delete",
                        "/admin/**");

        registry.addInterceptor(new ApprovedInterceptor())
                .addPathPatterns("/quiz/play",
                        "/quiz/check");
    }
}
