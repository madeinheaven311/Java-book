package com.d4c.www.mapper;

import com.d4c.www.entity.Clazz;
import com.d4c.www.util.MyBatisUtil;
import lombok.SneakyThrows;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;

public class CacheTest {

    SqlSessionFactory factory = MyBatisUtil.getFactory("mybatis.xml");



    @SneakyThrows
    @Test
    public void levelOneCache() {


        SqlSession SessionA = factory.openSession();
        SqlSession SessionB = factory.openSession();

        ClazzMapper mapperA01 = SessionA.getMapper(ClazzMapper.class);
        ClazzMapper mapperA02 = SessionA.getMapper(ClazzMapper.class);

        ClazzMapper mapperB01 = SessionB.getMapper(ClazzMapper.class);

        Clazz condition = new Clazz();
        condition.setClazzId(1);

        System.out.println(mapperA01.select(condition));

        System.out.println(mapperA02.select(condition));

        System.out.println(mapperB01.select(condition));

        SessionA.close();
        SessionB.close();

    }


    @SneakyThrows
    @Test
    public void levelTwoCache() {


        SqlSession SessionA = factory.openSession();
        SqlSession SessionB = factory.openSession();

        ClazzMapper mapperA01 = SessionA.getMapper(ClazzMapper.class);

        ClazzMapper mapperB01 = SessionB.getMapper(ClazzMapper.class);

        Clazz condition = new Clazz();
        condition.setClazzId(1);

        System.out.println(mapperA01.select(condition));

        SessionA.commit();

        System.out.println(mapperB01.select(condition));

        SessionA.close();
        SessionB.close();

    }
}
