package com.d4c.www.mapper;

import com.d4c.www.entity.Clazz;
import com.d4c.www.util.MyBatisUtil;
import lombok.SneakyThrows;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.After;
import org.junit.Test;

import java.util.Date;

public class ClazzMapperTest {

    //获取一个单例的工厂
    SqlSessionFactory factory = MyBatisUtil.getFactory("mybatis.xml");

    // 通过SqlSessionFactory开启一个具有事务保护的session会话，用于和数据库打交道
    // 若使用 `sessionFactory.openSession(true)` 则表示开启一个没有事务保护的session会话
    SqlSession sqlSession = factory.openSession();

    // 从会话中获取Mapper接口（因为主配中已经扫描了所有Mapper接口，所以可以直接获取）
    ClazzMapper clazzMapper = sqlSession.getMapper(ClazzMapper.class);


    @SneakyThrows
    @Test
    public void testSelect(){
        Clazz  clazz = new Clazz();
        // 调用Mapper接口方法，进行添加操作并打印操作结果
        clazz.setClazzName("0524班级");

        clazzMapper.select(clazz).forEach(System.out::println);


    }

    @SneakyThrows
    @Test
    public void testSelectPlus(){
        Clazz  clazz = new Clazz();
        // 调用Mapper接口方法，进行添加操作并打印操作结果
        clazz.setClazzId(4);

        clazzMapper.selectPlus(clazz).forEach(System.out::println);


    }
    @SneakyThrows
    @Test
    public void testInsert(){
        Clazz  clazz = new Clazz();
        clazz.setClazzName("0526班级");
        clazz.setCreateTime(new Date());
        clazz.setModifyTime(new Date());
        //调用方法
        System.out.println(clazzMapper.insert(clazz));
        sqlSession.commit();

        System.out.println(clazz);
    }


    @SneakyThrows
    @Test
    public void testUpdate(){
        Clazz  field = new Clazz();
        field.setClazzName("9999班");
        Clazz  condition = new Clazz();
        condition.setClazzId(2);

        //调用方法
        System.out.println(clazzMapper.update(field, condition));
        sqlSession.commit();

        System.out.println(field);

    }


    @SneakyThrows
    @Test
    public void testDelete(){

        Clazz  clazz = new Clazz();
        clazz.setClazzName("0521班级");

        System.out.println(clazzMapper.delete(clazz));
        sqlSession.commit();

        System.out.println(1);
    }


    @SneakyThrows
    @Test
    public void testBatchDelete(){
        Integer[] clazzIds = {1, 2, 3};
        System.out.println(clazzMapper.BatchDelete(clazzIds));
        sqlSession.commit();
    }


    @After
    public void after(){
        // 关闭会话，释放资源
        sqlSession.close();

    }

}
