package com.d4c.controller;


import com.d4c.dto.LoginDTO;
import com.d4c.entity.result.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping( value = "/api/v1/simpleParam")
public class SimpleParamController {

    @RequestMapping("/numberParam")
    public Result numberParam(Byte b, Short s, Integer i, Long lo,
                              Double d, Float f) {
        return new Result(b + ", " + s + ", " + i + ", " + lo + ", " + d + ", " + f);
    }

    @RequestMapping("/stringParam")
    public Result StringParam( @RequestParam( value = "uname") String name  ){
        return new Result(name);
    }

    @RequestMapping("/dateParam")
    public Result DateParam(Date birth ){
        return new Result(birth.toString());
    }

    @RequestMapping("/arrayParam")
    public Result ArrayParam( Integer[] ids ){
        return new Result(ids);
    }

    @RequestMapping("/booleanParam")
    public Result BooleanParam( @RequestParam( required = false, defaultValue = "true") Boolean flag ){
        return new Result(flag);
    }


    @RequestMapping("dtoParam")
    public Result dtoParam(LoginDTO loginDTO, LoginDTO inLoginDTO ){
        Map<String, Object> resultMap = new HashMap<>(2);
        resultMap.put("账号", loginDTO.getUsername());
        resultMap.put("密码", loginDTO.getPassword());

        loginDTO.setLoginDTO( inLoginDTO );

        resultMap.put("内部账号", loginDTO.getLoginDTO().getUsername());
        resultMap.put("内部密码", loginDTO.getLoginDTO().getPassword());

        return new Result(resultMap);
    }



}
