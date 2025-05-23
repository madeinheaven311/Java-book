package com.d4c.www.mapper;

import com.d4c.www.entity.Episode;

import java.util.List;

public interface EpisodeMapper {
    int insert(Episode episode);

    /**
     * 按条件查询Episode记录
     *
     * @param condition 查询条件
     * @return 多条Episode记录
     */
    List<Episode> select(Episode condition);

    /**
     * 按条件修改Episode记录
     *
     * @param episode   修改字段
     * @param condition 修改条件
     * @return 影响条目数
     */
    int update(Episode episode, Episode condition);

    /**
     * 按条件删除Episode记录
     *
     * @param condition 删除条件
     * @return 影响条目数
     */
    int delete(Episode condition);

    /**
     * 按Episode主键数组批量删除多条Episode记录
     *
     * @param episodeIds episode主键数组
     * @return 影响条目录
     */
    int deleteByEpisodeIds(Integer[] episodeIds);

}
