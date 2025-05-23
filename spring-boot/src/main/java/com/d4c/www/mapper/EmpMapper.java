package com.d4c.www.mapper;


import com.d4c.www.entity.Dept;
import com.d4c.www.entity.Emp;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmpMapper {

    //插入员工
    @Insert("insert into springboot.emp(name, age, fk_dept_id, created, updated) values (#{name},#{age},#{fkDeptId},#{created},#{updated})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert( Emp emp );

    //按条件（部门，序号，名字，年龄等）删除员工信息
    int delete(Emp emp);

    //按照名字，部门编号，id等查询符合条件的所有员工的所有信息
    List<Emp> select(Emp emp);

    //全部查询
    List<Emp> selectAll();

    //按条件更新员工信息
    int update(Emp emp, Emp condition);

}
