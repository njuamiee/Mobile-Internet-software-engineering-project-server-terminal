package org.example.server.config;


import org.example.server.Interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //登录与注册接口取消拦截
       /* registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/api/users/register")
                .excludePathPatterns("/api/users/login")
                .excludePathPatterns("/api/news")
                .excludePathPatterns("/api/news/create")
                .excludePathPatterns("/oss/upload")
                .order(1);*/
    }
}