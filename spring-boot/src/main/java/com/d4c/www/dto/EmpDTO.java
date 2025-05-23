package com.d4c.www.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Schema(name = "员工", description = "用于记录公司员工信息")
@Data
public class EmpDTO implements Serializable {


    @Schema(description = "员工编号", required = true, example = "0")
    private Long id;
    @Schema(description = "员工名字", required = true, example = "刘能")
    private String name;
    @Schema(description = "员工年龄", required = true, example = "18")
    private Long age;
    @Schema(description = "所属部门编号", required = true, example = "1")
    private Long fkDeptId;

}
