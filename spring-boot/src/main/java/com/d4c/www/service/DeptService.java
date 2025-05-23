package com.d4c.www.service;

import com.d4c.www.entity.Dept;

import java.util.List;

public interface DeptService {

    Dept insert(  Dept dept );
    Dept selectByDeptId( Long deptId );
    void updateByDeptId( Dept dept, Long deptId );
    void deleteByDeptId( Long deptId );

}
