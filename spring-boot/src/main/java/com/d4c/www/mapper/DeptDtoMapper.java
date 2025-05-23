package com.d4c.www.mapper;

import com.d4c.www.dto.Dept;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeptDtoMapper {

    //注解模式
    @Options(useGeneratedKeys = true, keyProperty = "deptId")
    @Insert("insert into springboot.dept (dept_name) values (#{deptName})")
    int insert(Dept dept);

    @Select("select * from dept where dept_id = #{param1}")
    Dept selectByDeptId( Long deptId );

    @Update("update dept set dept_name = #{param1.deptName} where dept_id = #{param2}")
    void updateByDeptId( Dept dept, Long deptId );

    @Delete("delete from dept where dept_id = #{param1}")
    void deleteByDeptId( Long deptId );

    //xml模式
    Dept select(Dept condition);

    List<Dept> selectAll( );

}
