package com.d4c.www.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class VideoOrder implements Serializable {
    private Integer videoOrderId;
    private Integer fkVideoId;
    private Integer fkOrderId;
    private Integer fkUserId;
    private Date createTime;
    private Date modifyTime;
    /** 每条中间表记录对应一个视频，1:1 */
    private Video video;
    /** 每条中间表记录对应一个订单，1:1 */
    private Order order;
    /** 每条中间表记录对应一个用户，1:1 */
    private User user;
}
