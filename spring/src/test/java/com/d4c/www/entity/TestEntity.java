package com.d4c.www.entity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@ContextConfiguration("classpath:spring.xml")
public class TestEntity {

    @Test
    public void cat() {
        // 造鱼缸: 创建Spring容器，需要读取主配，可省略 `classpath:` 前缀
        ClassPathXmlApplicationContext context
                = new ClassPathXmlApplicationContext("classpath:spring.xml");
        // 捞鱼: 通过ID值/Name值/Class值从Spring容器中获取bean实例
        Cat cat01 = (Cat) context.getBean("cat");
        Cat cat02 = (Cat) context.getBean("JiaFeiMao");
        Cat cat03 = context.getBean(Cat.class);
        // 用鱼: 调用bean实例方法
        System.out.println("byId: " + cat01);
        System.out.println("byName: " + cat02);
        System.out.println("byClass: " + cat03);
        // 砸鱼缸: 关闭Spring容器
        context.close();
    }

    @Test
    public void dog() {
        // 造鱼缸: 创建Spring容器
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        // 捞鱼: 通过Class值从Spring容器中获取bean实例
        Dog dog = context.getBean(Dog.class);
        // 用鱼
        System.out.println(dog);
        // 砸鱼缸: 关闭Spring容器
        context.close();
    }

    @Test
    public void mouse() {
        // 造鱼缸: 创建Spring容器
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        // 捞鱼: 通过Class值从Spring容器中获取bean实例
        Mouse mouse = context.getBean(Mouse.class);
        // 用鱼
        System.out.println(mouse);
        // 砸鱼缸: 关闭Spring容器
        context.close();
    }



}
