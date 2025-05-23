package com.d4c.www.mapper;

import com.d4c.www.entity.Clazz;

import java.util.List;

public interface ClazzMapper {

    List<Clazz> select( Clazz clazz );

//    涉及多表并行--join联级
    List<Clazz> selectPlus( Clazz clazz );

//    涉及多表并行--分步进行
    List<Clazz> selectByStep( Clazz clazz );

//    用于分布查询，接收student中的查询传来的clazzId做主键查询,student->clazz
    List<Clazz> selectByClazzId( Clazz clazz );

    int insert( Clazz clazz );

    int update ( Clazz field, Clazz condition );

    int delete( Clazz clazz );

    int BatchDelete( Integer[] clazzIds );



}
