package com.d4c;


import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import static org.springframework.boot.SpringApplication.run;

@SpringBootApplication(exclude = {
        DataSourceAutoConfiguration.class,
        DruidDataSourceAutoConfigure.class
})
@MapperScan("com.d4c.mapper")
public class ShardingApplication {
    public static void main(String[] args) {
        run(ShardingApplication.class, args);
    }
}
