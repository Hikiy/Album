package com.hiki.wxmessage.config;

import com.hiki.wxmessage.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author ：hiki
 * 2019/8/17 10:23
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    /**
     * 自定义拦截规则
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // addPathPatterns - 用于添加拦截规则
        // excludePathPatterns - 用户排除拦截

//        registry.addInterceptor(new LoginInterceptor())
//                .addPathPatterns("/admin/**");

//                .excludePathPatterns("/index.html", "/", "/user/login");
    }
}
