package com.d4c.www.config;

import com.d4c.www.component.MyInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Autowired
    private MyInterceptor myInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(myInterceptor).
                //拦截所有如下规范的访问
                addPathPatterns("/api/v0/**").
                //如下访问格式例外，放行如下格式访问
                excludePathPatterns("/api/v0/noInterceptor");
    }
}
