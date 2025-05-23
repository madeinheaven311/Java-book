package com.d4c.www.config;


import cn.hutool.json.JSONUtil;
import com.d4c.www.entity.Duck;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.w3c.dom.ls.LSOutput;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = DuckConfig.class)
public class DuckConfigTest {

    @Autowired
    Duck duck2;

    @Test
    public void TestDuck(){

        System.out.println(duck2);

    }



}
