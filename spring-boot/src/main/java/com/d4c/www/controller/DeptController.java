package com.d4c.www.controller;


import com.d4c.www.dto.Dept;
import com.d4c.www.service.DeptDtoService;
import com.d4c.www.service.DeptService;
import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Tag(name = "部门控制器", description = "对公司部门进行查看，删除，增加等")
@RestController
@RequestMapping("api/v1/dept")
public class DeptController {

    @Autowired
    private DeptDtoService deptService;

    @Operation(summary = "添加部门", description = "添加一个部门")
    @PostMapping("/insert")
    public Dept insert( @RequestBody Dept dept){
        deptService.insert( dept );
        return dept;
    }

    @Operation(summary = "删除部门", description = "根据部门编号删除一个部门")
    @GetMapping("/deleteById/{id}")
    public String deleteById( @Parameter (description = "要删除部门编号") @PathVariable Long id ){
        deptService.deleteByDeptId(id);
        return "已成功删除"+id+"号部门";
    }

    @Operation(summary = "更新部门", description = "根据部门编号更新一个部门")
    @PostMapping("/updateById/{id}")
    public String updateById( @Parameter(  description = "要更新的部门名称") @RequestBody Dept dept , @PathVariable @Parameter( description = "要更新的部门编号") Long id){
        deptService.updateByDeptId( dept , id );
        return "已成功添加"+dept.getDeptName();
    }

    @Operation(summary = "查询部门", description = "根据部门编号查询一个部门")
    @GetMapping("/selectById/{id}")
    public Dept selectById(  @Parameter ( description = "要查询部门编号") @PathVariable Long id ){
        return deptService.selectByDeptId(id);
    }

}