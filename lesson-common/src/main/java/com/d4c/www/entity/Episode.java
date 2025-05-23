package com.d4c.www.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Episode implements Serializable {
    private Integer episodeId;
    private String episodeTitle;
    private String episodeInfo;
    private String episodeUrl;
    private Integer fkChapterId;
    private Integer episodeIndex;
    private Date createTime;
    private Date modifyTime;
}
