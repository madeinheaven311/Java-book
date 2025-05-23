package com.d4c.www.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/** @author JoeZhou */
@Schema(name = "部门", description = "用于记录公司部门名字和部门序号")
@Data
public class Dept implements Serializable {

    @Schema(description = "部门的编号", required = true, example = "1")
    private Long deptId;

    @Schema(description = "部门的名字", required = true, example = "财务部")
    private String deptName;

}
