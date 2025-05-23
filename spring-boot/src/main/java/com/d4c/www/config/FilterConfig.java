package com.d4c.www.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<Filter> logFilter() {
        FilterRegistrationBean<Filter> registration = new FilterRegistrationBean<>();
        registration.setFilter(new CustomFilter());
        registration.addUrlPatterns("/*");
        registration.setOrder(1); // 设置过滤器顺序
        registration.setName("logFilter");
        return registration;
    }

    // 自定义过滤器实现
    public static class CustomFilter implements Filter {
        @Override
        public void doFilter(ServletRequest request, ServletResponse response,
                            FilterChain chain) throws IOException, ServletException {
            // 示例：跨域处理
            HttpServletResponse res = (HttpServletResponse) response;
            res.setHeader("Access-Control-Allow-Origin", "*");
            res.setHeader("Access-Control-Allow-Methods", "POST, GET");
            
            chain.doFilter(request, response);
        }
    }
}
