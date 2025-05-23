package com.d4c.www.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;


@Schema(name = "添加猪实体参数", description = "用于添加猪的Param实体类参数")
@Data
public class Pig  implements Serializable {

    @Schema(description = "猪的名字", required = true, example = "佩奇")
    private String pigName;

}
