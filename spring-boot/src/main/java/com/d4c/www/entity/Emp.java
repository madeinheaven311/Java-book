package com.d4c.www.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


@Data
public class Emp implements Serializable {


    private Long id;

    private String name;

    private Long age;

    private Long fkDeptId;

    private Date created = new Date();

    private Date updated = new Date();

}
