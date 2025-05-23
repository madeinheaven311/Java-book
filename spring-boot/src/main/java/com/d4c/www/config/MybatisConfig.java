package com.d4c.www.config;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.d4c.www.mapper")
public class MybatisConfig {
}
