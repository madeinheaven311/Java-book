package com.d4c.www.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class Video implements Serializable {
    private Integer videoId;
    private String videoTitle;
    private String author;
    private String videoInfo;
    private String summaryImage;
    private String coverImage;
    private Double price;
    private Integer star;
    private Integer videoDeleted;
    private Date createTime;
    private Date modifyTime;
    /** 每个视频拥有多个章，1:N */
    private List<Chapter> chapters;
}