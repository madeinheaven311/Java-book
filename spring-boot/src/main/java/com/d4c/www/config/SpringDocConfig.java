package com.d4c.www.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/** @author JoeZhou */
@Configuration
public class SpringDocConfig {

    /** 通用信息展板 */
    @Bean
    public OpenAPI commonInfo() {
        return new OpenAPI().info(new Info()
                .title("SpringDocTest")
                .description("<em>一段200字的描述</em>")
                .version("v1.0.1")
                .contact(new Contact().email("yy06200210@163.com").name("Shrawn").url("http:/localhost:8070")));
    }

    /** 整合Knife4j必须添加这个@Bean */
    @Bean
    public GroupedOpenApi publicApi(){
        return GroupedOpenApi.builder()
                .setGroup("第一组")
                .pathsToMatch("/**")
                .build();
    }
}
