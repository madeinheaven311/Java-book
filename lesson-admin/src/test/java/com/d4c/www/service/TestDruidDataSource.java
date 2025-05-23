package com.d4c.www.service;


import com.alibaba.druid.pool.DruidDataSource;
import lombok.SneakyThrows;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestDruidDataSource {

    @SneakyThrows
    @Test
    public void druidDataSource() {
        // 造鱼缸
        ClassPathXmlApplicationContext context
                = new ClassPathXmlApplicationContext("classpath:spring.xml");
        // 捞鱼
        DruidDataSource dataSource = context.getBean(DruidDataSource.class);
        // 用鱼: 获取连接并测试是否关闭
        System.out.println(dataSource.getConnection().isClosed() ? "失败" : "成功");
        System.out.println("驱动串: " + dataSource.getDriverClassName());
        System.out.println("连接串: " + dataSource.getUrl());
        System.out.println("账号值: " + dataSource.getUsername());
        System.out.println("密码值: " + dataSource.getPassword());
        // 砸鱼缸
        context.close();
    }
}
