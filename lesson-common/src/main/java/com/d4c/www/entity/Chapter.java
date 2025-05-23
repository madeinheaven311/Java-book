package com.d4c.www.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class Chapter implements Serializable {
    private Integer chapterId;
    private String chapterTitle;
    private String chapterInfo;
    private Integer fkVideoId;
    private Integer chapterIndex;
    private Date createTime;
    private Date modifyTime;
    /** 每一章拥有多集，1:N */
    private List<Episode> episodes;
}
