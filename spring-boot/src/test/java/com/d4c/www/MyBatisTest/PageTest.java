package com.d4c.www.MyBatisTest;

import com.d4c.www.SpringBootApp;
import com.d4c.www.entity.Dept;
import com.d4c.www.mapper.DeptMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootApp.class)
public class PageTest {

    @Autowired
    private DeptMapper deptMapper;

    @Test
    public void page(){
        // 开启分页功能
        PageHelper.startPage(2,3);
        List<Dept> depts = deptMapper.selectAll();
        PageInfo<Dept> pageInfo = new PageInfo<>(depts);
        System.out.println("当前第几页: " + pageInfo.getPageNum());
        System.out.println("每页多少条: " + pageInfo.getPageSize());
        System.out.println("一共多少条: " + pageInfo.getTotal());
        System.out.println("一共多少页: " + pageInfo.getPages());
        System.out.println("分页数据集: ");
        for (Dept dept : pageInfo.getList()) {
            System.out.println("\t" + dept);
        }
    }
}