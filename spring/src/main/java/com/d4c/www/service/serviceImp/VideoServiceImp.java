package com.d4c.www.service.serviceImp;

import com.d4c.www.entity.Dog;
import com.d4c.www.mapper.VideoMapper;
import com.d4c.www.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class VideoServiceImp implements VideoService {

    @Autowired
    VideoMapper videoMapper;
    @Autowired
    Dog dog;

    @Override
    public void insert() {
        System.out.println("测试成功");
        videoMapper.insert();
        System.out.println(dog);
    }
}
