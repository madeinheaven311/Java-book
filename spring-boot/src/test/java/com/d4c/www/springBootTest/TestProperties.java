package com.d4c.www.springBootTest;


import cn.hutool.json.JSONUtil;
import com.d4c.www.SpringBootApp;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootApp.class)
@PropertySource("classpath:application.properties")
public class TestProperties {

    @Value("${spring.application.name}")
    private String applicationName;

    @Test
    public void test(){
        System.out.println(applicationName);
    }
}
