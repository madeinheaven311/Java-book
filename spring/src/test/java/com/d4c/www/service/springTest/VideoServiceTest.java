package com.d4c.www.service.springTest;



import com.d4c.www.service.VideoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/** 使用spring-test,整合了junit，可以自动启动项目，制造鱼缸，读取主配文件 **/
@RunWith(SpringRunner.class)
@ContextConfiguration("classpath:spring.xml")
public class VideoServiceTest {

    @Autowired
    private VideoService videoService;

    @Test
    public void addVideo() {
        videoService.insert();
    }
}
