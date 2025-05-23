package com.d4c.www.mapper;

import com.d4c.www.entity.Video;

import java.util.List;

public interface VideoMapper {

    /**
     * 添加Video记录
     *
     * @param video 添加字段
     * @return 影响条目数
     */
    int insert(Video video);

    /**
     * 按条件查询Video记录
     *
     * @param condition 查询条件
     * @return 多条Video记录
     */
    List<Video> select(Video condition);

    /**
     * 按条件修改Video记录
     *
     * @param video     修改字段
     * @param condition 修改条件
     * @return 影响条目数
     */
    int update(Video video, Video condition);

    /**
     * 按条件删除Video记录
     *
     * @param condition 删除条件
     * @return 影响条目数
     */
    int delete(Video condition);

    /**
     * 按Video主键数组批量删除多条Video记录
     *
     * @param videoIds Video主键数组
     * @return 影响条目录
     */
    int deleteByVideoIds(Integer[] videoIds);
}
