package com.d4c.www.AopTest;

import com.d4c.www.SpringBootApp;
import com.d4c.www.service.serviceImpl.AopServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootApp.class)
public class TestAop {

    @Autowired(required=false)
    private AopServiceImpl aopServiceImpl;

    @Test
    public void test01() {
        System.out.println(aopServiceImpl.test("赵四"));
    }

    @Test
    public void test02() {
        System.out.println(aopServiceImpl.test(null));
    }
}

