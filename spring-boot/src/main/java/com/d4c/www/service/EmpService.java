package com.d4c.www.service;

import com.d4c.www.entity.Dept;
import com.d4c.www.entity.Emp;

import java.util.List;

public interface EmpService {

    Emp insert( Emp emp );

    List<Emp> select(Emp emp );

    List<Emp> selectAll();

    void update(Emp emp, Emp condition );

    void delete( Emp emp );
}
