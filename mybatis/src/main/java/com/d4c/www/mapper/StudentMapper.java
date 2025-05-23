package com.d4c.www.mapper;

import com.d4c.www.entity.Student;

import java.util.List;

public interface StudentMapper {

    List<Student> select( Student student );

    //    涉及多表并行--join联级
    List<Student> selectPlus( Student student );

    //    涉及多表并行--分步进行
    List<Student> selectByStep( Student student );

    //    用于分布查询，接收clazz中的查询传来的clazzId做主键查询,clazz->student
    List<Student> selectByClazzId( Student student );


    int insert( Student student);

    int update( Student field, Student condition );

    int delete( Student student );

    int BatchDelete( List<Integer> studentIds );

}
