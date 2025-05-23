package com.d4c.www.controller;

import com.d4c.www.dto.Pig;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "猪", description = "猪控制器")
@RestController
public class SpringDocController {

    @Operation(summary = "添加猪", description = "添加一只猪")
    @PostMapping("/test/springDoc/{num}")
    public String springDoc( @PathVariable("num") Long pigId ){
        return "springDoc测试成功";
    }
}