package com.d4c.www.mapper;

import com.d4c.www.entity.VideoOrder;

import java.util.List;

public interface VideoOrderMapper {
    /**
     * 添加VideoOrder记录
     *
     * @param videoOrder 添加字段
     * @return 影响条目数
     */
    int insert(VideoOrder videoOrder);

    /**
     * 按条件查询VideoOrder记录
     *
     * @param condition 查询条件
     * @return 多条VideoOrder记录
     */
    List<VideoOrder> select(VideoOrder condition);

    /**
     * 按条件修改VideoOrder记录
     *
     * @param videoOrder 修改字段
     * @param condition  修改条件
     * @return 影响条目数
     */
    int update(VideoOrder videoOrder, VideoOrder condition);

    /**
     * 按条件删除VideoOrder记录
     *
     * @param condition 删除条件
     * @return 影响条目数
     */
    int delete(VideoOrder condition);

    /**
     * 按VideoOrder主键数组批量删除多条VideoOrder记录
     *
     * @param videoOrderIds VideoOrder主键数组
     * @return 影响条目录
     */
    int deleteByVideoOrderIds(Integer[] videoOrderIds);
}
