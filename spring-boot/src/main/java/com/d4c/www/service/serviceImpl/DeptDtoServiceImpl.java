package com.d4c.www.service.serviceImpl;

import com.d4c.www.dto.Dept;
import com.d4c.www.mapper.DeptDtoMapper;
import com.d4c.www.mapper.DeptMapper;
import com.d4c.www.service.DeptDtoService;
import com.d4c.www.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class DeptDtoServiceImpl implements DeptDtoService {

    @Autowired
    private DeptDtoMapper deptMapper;


    @Override
    public Dept insert(Dept dept) {
        deptMapper.insert(dept);
        return dept;
    }


    @Override
    public Dept selectByDeptId(Long deptId) {

        return deptMapper.selectByDeptId(deptId);
    }


    @Override
    public void updateByDeptId(Dept dept, Long deptId) {
        deptMapper.updateByDeptId(dept,deptId);
    }


    @Override
    public void deleteByDeptId(Long deptId) {
        deptMapper.deleteByDeptId(deptId);
    }
}
