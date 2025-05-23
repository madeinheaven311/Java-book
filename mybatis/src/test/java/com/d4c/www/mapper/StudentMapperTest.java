package com.d4c.www.mapper;

import com.d4c.www.entity.Student;
import com.d4c.www.util.MyBatisUtil;
import lombok.SneakyThrows;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.After;
import org.junit.Test;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class StudentMapperTest {

    //获取一个单例的工厂
    SqlSessionFactory factory = MyBatisUtil.getFactory("mybatis.xml");

    // 通过SqlSessionFactory开启一个具有事务保护的session会话，用于和数据库打交道
    // 若使用 `sessionFactory.openSession(true)` 则表示开启一个没有事务保护的session会话
    SqlSession sqlSession = factory.openSession();

    // 从会话中获取Mapper接口（因为主配中已经扫描了所有Mapper接口，所以可以直接获取）
    StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);

    @SneakyThrows
    @Test
    public void testSelect(){
        Student student = new Student();
        student.setRealName("张三");

        // 调用Mapper接口方法，进行添加操作并打印操作结果
        studentMapper.select(student).forEach(System.out::println);


    }


    @SneakyThrows
    @Test
    public void testSelectPlus(){
        Student student = new Student();
        student.setRealName("二牛");

        // 调用Mapper接口方法，进行添加操作并打印操作结果
        studentMapper.selectPlus(student).forEach(System.out::println);


    }

    @SneakyThrows
    @Test
    public void testSelectByStep(){
        Student student = new Student();
        student.setRealName("二牛");

        // 调用Mapper接口方法，进行添加操作并打印操作结果
        studentMapper.selectByStep(student).forEach(System.out::println);


    }

    @SneakyThrows
    @Test
    public void testInsert(){
        Student student = new Student();
        student.setRealName("张三");
        student.setGender(1);
        student.setPhone("phone");
        student.setClazzId(0521);
        student.setCreateTime(new Date());
        student.setModifyTime(new Date());
        System.out.println(studentMapper.insert(student));
        //提交会话，DML会话需要手段提交，否则会滚回
        sqlSession.commit();

        System.out.println(student);

    }


    @SneakyThrows
    @Test
    public void testUpdate(){
        Student field = new Student();
        field.setRealName("二牛");
        Student condition = new Student();
        condition.setStudentId(2);
//        调用方法
        System.out.println(studentMapper.update(field,condition));
        //提交会话，DML会话需要手段提交，否则会滚回
        sqlSession.commit();

        System.out.println(field);
    }


    @SneakyThrows
    @Test
    public void testDelete(){
        Student student = new Student();
        student.setRealName("张三");
        //调用方法
        System.out.println(studentMapper.delete(student));
        sqlSession.commit();

        System.out.println(1);
    }


    @SneakyThrows
    @Test
    public void testBatchDelete(){
        List<Integer> studentIds = Arrays.asList( 88, 99, 100 );

        System.out.println(studentMapper.BatchDelete(studentIds));
        sqlSession.commit();
    }

    @After
    public void after(){
        // 关闭会话，释放资源
        sqlSession.close();

    }
}
