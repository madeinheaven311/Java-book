package com.d4c.www.aspect;

import com.d4c.www.entity.Dog;

public class MyService {

    public Dog baseMethod(String dogName) {
        System.out.println("baseMethod() 方法被调用，参数为: " + dogName);
        if (dogName == null) throw new RuntimeException("狗的名字不能为null");
        return new Dog();
    }

    public Dog roundMethod(String dogName) {
        System.out.println("roundMethod() 方法被调用，参数为: " + dogName);
        if (dogName == null) throw new RuntimeException("狗的名字不能为null");
        return new Dog();
    }
}
