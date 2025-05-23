package com.d4c.www.service;

import com.d4c.www.entity.Mouse;
import com.d4c.www.service.serviceImp.VideoServiceImp;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class VideoServiceTest {


    @Test
    public void insert() {
        // 造鱼缸: 创建Spring容器
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        // 捞鱼: 通过Class值从Spring容器中获取bean实例
        VideoService videoService = context.getBean(VideoService.class);
        // 用鱼
        videoService.insert();
        // 砸鱼缸: 关闭Spring容器
        context.close();
    }

}
