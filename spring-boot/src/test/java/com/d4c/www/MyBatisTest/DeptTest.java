package com.d4c.www.MyBatisTest;

import com.d4c.www.SpringBootApp;
import com.d4c.www.entity.Dept;
import com.d4c.www.mapper.DeptMapper;
import com.d4c.www.service.DeptService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootApp.class)
public class DeptTest {

    @Autowired
    private DeptMapper deptMapper;

    @Autowired
    private DeptService deptService;

    @Test
    public void insert() {
        Dept dept = new Dept();
        dept.setDeptName("kjjj");
        System.out.println(deptMapper.insert(dept));
    }

    @Test
    public void select() {
        Dept dept = new Dept();
        dept.setDeptId(3L);
        System.out.println(deptMapper.selectAll());
    }




    @Test
    public void crudWithCache() {
        Long deptId = 13L;
        Dept dept = new Dept();
        dept.setDeptName("MGM部");

        // 发送SQL
        System.out.println(deptService.selectByDeptId(deptId));
        // 不发送SQL
        System.out.println(deptService.selectByDeptId(deptId));
        // 发送SQL - 删除缓存
        deptService.updateByDeptId(dept, deptId);
        // 发送SQL
        System.out.println(deptService.selectByDeptId(deptId));
        // 发送SQL - 删除缓存
        deptService.deleteByDeptId(deptId);
        // 发送SQL - 添加缓存
        deptService.insert(dept);
        // 不发送SQL
        System.out.println(deptService.selectByDeptId(dept.getDeptId()));
    }


}