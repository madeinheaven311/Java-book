package com.d4c.www.service;

import com.d4c.www.dto.Dept;

public interface DeptDtoService {

    Dept insert(Dept dept );
    Dept selectByDeptId( Long deptId );
    void updateByDeptId( Dept dept, Long deptId );
    void deleteByDeptId( Long deptId );
}
