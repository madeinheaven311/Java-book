package com.d4c.www.entity;

import lombok.Data;
import java.io.Serializable;
import java.util.Date;

@Data
public class Banner implements Serializable {
    private Integer bannerId;
    private String bannerUrl;
    private Integer bannerIndex;
    private Date createTime;
    private Date modifyTime;
}

