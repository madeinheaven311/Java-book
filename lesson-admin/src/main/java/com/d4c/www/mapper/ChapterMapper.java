package com.d4c.www.mapper;

import com.d4c.www.entity.Chapter;

import java.util.List;

public interface ChapterMapper {

    /**
     * 添加Chapter记录
     *
     * @param chapter 添加字段
     * @return 影响条目数
     */
    int insert(Chapter chapter);

    /**
     * 按条件查询Chapter记录
     *
     * @param condition 查询条件
     * @return 多条Chapter记录
     */
    List<Chapter> select(Chapter condition);

    /**
     * 按条件修改Chapter记录
     *
     * @param chapter   修改字段
     * @param condition 修改条件
     * @return 影响条目数
     */
    int update(Chapter chapter, Chapter condition);

    /**
     * 按条件删除Chapter记录
     *
     * @param condition 删除条件
     * @return 影响条目数
     */
    int delete(Chapter condition);

    /**
     * 按Chapter主键数组批量删除多条Chapter记录
     *
     * @param chapterIds Chapter主键数组
     * @return 影响条目录
     */
    int deleteByChapterIds(Integer[] chapterIds);
}

