package com.d4c.www.util;

import lombok.SneakyThrows;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

public class MyBatisUtil {


    private static SqlSessionFactory sessionFactory;

    private MyBatisUtil(){}

    @SneakyThrows
    public static SqlSessionFactory getFactory( String filePath ){


        // 将主配文件转成输入字节流，创建会话工厂的时侯用
        if ( sessionFactory == null ) {

            synchronized (MyBatisUtil.class) {

                if ( sessionFactory == null ) {
                    // 通过SqlSessionFactoryBuilder创建SqlSessionFactory，创建时必须读取主配文件
                    InputStream resource = Resources.getResourceAsStream(filePath);

                    sessionFactory = new SqlSessionFactoryBuilder().build(resource);

                }
            }
        }
        return sessionFactory;
    }



}

