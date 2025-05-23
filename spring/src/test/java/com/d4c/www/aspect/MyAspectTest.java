package com.d4c.www.aspect;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyAspectTest {


    @Test
    public void baseAdvice() {
        ClassPathXmlApplicationContext context
                = new ClassPathXmlApplicationContext("spring.xml");
        MyService myService = context.getBean(MyService.class);
        System.out.println(myService.baseMethod("欧迪"));
        //测试异常通知
        // System.out.println(myService.test(null));
        context.close();
    }

    @Test
    public void aroundAdvice() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        MyService myService = context.getBean(MyService.class);
        System.out.println(myService.roundMethod("欧迪"));
        //测试异常通知
        // System.out.println(myService.roundMethod(null));
        context.close();
    }
}
