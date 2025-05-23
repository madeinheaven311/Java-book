package com.d4c.www.processor;

import lombok.SneakyThrows;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class MyBeanPostProcessor implements BeanPostProcessor {

    /** 这个方法会在Spring容器中所有bean的初始化之前执行 */
    @SneakyThrows
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        System.out.println("处理器: " + beanName + ".postProcessBeforeInitialization()");
        return bean;
    }

    /** 这个方法会在Spring容器中所有bean的初始化之后执行 */
    @SneakyThrows
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        System.out.println("处理器: " + beanName + ".postProcessAfterInitialization()");
        return bean;
    }
}
