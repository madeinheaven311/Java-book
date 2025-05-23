package com.d4c.controller;

import com.d4c.dto.UserInsertDTO;
import com.d4c.entity.result.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/resultBody/")
public class RequestBodyController {

    @PostMapping("insert")
    public Result insert(@RequestBody UserInsertDTO dto) {
        return new Result(Map.of("请求参数", dto));
    }

}
