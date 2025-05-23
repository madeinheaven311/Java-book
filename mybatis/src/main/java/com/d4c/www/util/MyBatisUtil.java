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


       //节省开销
        if ( sessionFactory == null ) {
            //保证只有一个线程创建工厂
            synchronized (MyBatisUtil.class) {
                //静态加if null 特判，构造单例形式
                if ( sessionFactory == null ) {
                    // 将主配文件转成输入字节流，创建会话工厂的时侯用
                    InputStream resource = Resources.getResourceAsStream(filePath);
                    // 通过SqlSessionFactoryBuilder创建SqlSessionFactory，创建时必须读取主配文件
                    sessionFactory = new SqlSessionFactoryBuilder().build(resource);

                }
            }
        }
        return sessionFactory;
    }



}
