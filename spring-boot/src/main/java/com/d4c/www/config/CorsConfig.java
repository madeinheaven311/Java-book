package com.d4c.www.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/** @author JoeZhou */
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 允许跨域访问我所有的URL路径
        registry.addMapping("/**")
                // 允许所有的请求来源对我进行跨域访问
                .allowedOrigins("*")
                // 允许发送Cookie信息
                .allowCredentials(true)
                // 允许所有的请求类型对我进行跨域访问，单独设置时逗号分隔，且必须使用大写字母
                .allowedMethods("*")
                // 允许请求中携带任意的Header信息
                .allowedHeaders("*")
                // 设置预检请求的超时时间，默认1800秒
                .maxAge(3600 * 24);
    }
}