package com.d4c.www.ServiceTest;

import com.d4c.www.SpringBootApp;
import com.d4c.www.entity.Dept;
import com.d4c.www.service.DeptService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author JoeZhou
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootApp.class)
public class DeptTest {

    @Autowired
    private DeptService deptService;

    @Test
    public void insert() {
        Dept dept = new Dept();
        dept.setDeptName("ccccc");
        System.out.println(deptService.insert(dept));
    }

    @Test
    public void selectByDeptId() {
        System.out.println(deptService.selectByDeptId(19L));
    }

    @Test
    public void updateByDeptId() {
        Dept dept = new Dept();
        dept.setDeptName("人力资源部2");
        deptService.updateByDeptId(dept, 19L);
    }

    @Test
    public void deleteByDeptId() {
        deptService.deleteByDeptId(19L);
    }

}
