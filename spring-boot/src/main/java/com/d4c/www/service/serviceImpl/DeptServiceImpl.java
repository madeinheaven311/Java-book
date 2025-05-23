package com.d4c.www.service.serviceImpl;

import com.d4c.www.entity.Dept;
import com.d4c.www.mapper.DeptMapper;
import com.d4c.www.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;



@CacheConfig( cacheNames = "dept")
@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;

    @CachePut( key="#result.deptId", condition = "#result.deptId != null")
    @Override
    public Dept insert(Dept dept) {
        deptMapper.insert(dept);
        return dept;
    }

    @Cacheable( key = "#deptId", condition = "#deptId != null")
    @Override
    public Dept selectByDeptId(Long deptId) {

        return deptMapper.selectByDeptId(deptId);
    }

    @CacheEvict( key="#deptId", condition = "#deptId != null" )
    @Override
    public void updateByDeptId(Dept dept, Long deptId) {
            deptMapper.updateByDeptId(dept,deptId);
    }

    @CacheEvict( key="#deptId", condition = "#deptId != null" )
    @Override
    public void deleteByDeptId(Long deptId) {
            deptMapper.deleteByDeptId(deptId);
    }
}
