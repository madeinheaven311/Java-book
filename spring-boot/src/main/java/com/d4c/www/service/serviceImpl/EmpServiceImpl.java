package com.d4c.www.service.serviceImpl;

import com.d4c.www.entity.Dept;
import com.d4c.www.entity.Emp;
import com.d4c.www.mapper.EmpMapper;
import com.d4c.www.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;

    @Override
    public Emp insert(Emp emp) {
        empMapper.insert(emp);
        return emp;
    }

    @Override
    public void delete(Emp emp) {
        empMapper.delete(emp);
    }

    @Override
    public List<Emp> select(Emp emp) {
        return empMapper.select(emp);
    }

    @Override
    public List<Emp> selectAll() {
        return empMapper.selectAll();
    }

    @Override
    public void update(Emp emp, Emp condition) {
        empMapper.update(emp, condition);
    }
}
