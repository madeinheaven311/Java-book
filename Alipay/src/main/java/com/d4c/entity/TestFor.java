package com.d4c.entity;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;


@Component
public class TestFor implements FactoryBean {

    @Resource
    private ResourceT r;

    @Override
    public Object getObject() throws Exception {
        return r ;
    }

    @Override
    public Class<?> getObjectType() {
        return null;
    }

//    @Autowired
//    private Resource resource2;

}
