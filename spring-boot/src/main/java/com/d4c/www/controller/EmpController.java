package com.d4c.www.controller;


import com.d4c.www.dto.EmpDTO;
import com.d4c.www.entity.Emp;
import com.d4c.www.service.EmpService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@Tag(name = "员工控制器", description = "对员工进行查看，删除，增加等")
@RestController
@RequestMapping("api/v0/emp")
public class EmpController {

    @Autowired
    EmpService empService;


    @Operation(summary = "添加员工", description = "添加一个员工")
    @PostMapping("/insert")
    public Emp insert( @RequestBody EmpDTO empDto ){
        Emp emp = new Emp();
        emp.setAge(empDto.getAge());
        emp.setName(empDto.getName());
        emp.setFkDeptId(empDto.getFkDeptId());
        return empService.insert(emp);
    }

    @Operation(summary = "删除员工", description = "删除员工")
    @PostMapping("/delete")
    public String delete( @RequestBody EmpDTO empDto){
        Emp emp =  new Emp();
        emp.setAge(empDto.getAge());
        emp.setName(empDto.getName());
        emp.setFkDeptId(empDto.getFkDeptId());
        emp.setId(empDto.getId());
        empService.delete(emp);
        return "删除该员工成功";
    }

    @Operation(summary = "按条件查询员工", description = "按条件查询员工信息")
    @PostMapping("/select")
    public List<Emp> select(@RequestBody EmpDTO empDto ){
        Emp emp =  new Emp();
        emp.setAge(empDto.getAge());
        emp.setName(empDto.getName());
        emp.setFkDeptId(empDto.getFkDeptId());
        emp.setId(empDto.getId());
        return empService.select(emp);


    }

    @Operation(summary = "查询所有员工", description = "查询所有员工信息")
    @PostMapping("/selectAll")
    public List<Emp> selectAll(){
        return empService.selectAll();
    }

    @Operation(summary = "更改员工", description = "更改员工信息")
    @PostMapping("/update/{id}")
    public String update(@RequestBody EmpDTO empDto, @PathVariable Long id){
        Emp emp =  new Emp();
        Emp condition = new Emp();
        emp.setAge(empDto.getAge());
        emp.setName(empDto.getName());
        emp.setFkDeptId(empDto.getFkDeptId());
        condition.setId(id);

        empService.update( emp, condition );
        return "修改员工成功";
    }
}
