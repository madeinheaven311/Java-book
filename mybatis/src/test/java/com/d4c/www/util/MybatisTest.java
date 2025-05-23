package com.d4c.www.util;

import lombok.SneakyThrows;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;
import java.sql.Connection;

public class MybatisTest {

    /** 测试MyBatis连接是否成功 */
    @SneakyThrows
    @Test
    public void testConnection() {

        // 主配文件的位置: 在创建会话工厂的时侯，需要读取主配
        String filePath = "mybatis.xml";

        // 将主配文件转成输入字节流，创建会话工厂的时侯用
        InputStream resource = Resources.getResourceAsStream(filePath);

        // 通过SqlSessionFactoryBuilder创建SqlSessionFactory，创建时必须读取主配文件
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder()
                .build(resource);

        // 通过SqlSessionFactory开启一个具有事务保护的session会话，用于和数据库打交道
        // 若使用 `sessionFactory.openSession(true)` 则表示开启一个没有事务保护的session会话
        SqlSession sqlSession = sessionFactory.openSession();

        // 从SqlSession会话中获取一个连接
        Connection connection = sqlSession.getConnection();

        // 判断连接是否成功
        System.out.println(connection.isClosed() ? "连接失败" : "连接成功");

        // 关闭会话，清空一级缓存，可使用 `try-with-resource` 结构
        sqlSession.close();
    }

    @SneakyThrows
    @Test
    public void testConnectionPro() {

        SqlSessionFactory sessionFactory = MyBatisUtil.getFactory("mybatis.xml");

        // 若使用 `sessionFactory.openSession(true)` 则表示开启一个没有事务保护的session会话
        SqlSession sqlSession = sessionFactory.openSession();

        // 从SqlSession会话中获取一个连接
        Connection connection = sqlSession.getConnection();

        // 判断连接是否成功
        System.out.println(connection.isClosed() ? "连接失败" : "连接成功");

        // 关闭会话，清空一级缓存，可使用 `try-with-resource` 结构
        sqlSession.close();

    }

}
