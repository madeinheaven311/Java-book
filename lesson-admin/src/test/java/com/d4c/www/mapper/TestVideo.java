package com.d4c.www.mapper;

import com.d4c.www.entity.Video;
import com.d4c.www.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Test;

import java.util.Date;

public class TestVideo {
    private final SqlSession sqlSession = MyBatisUtil.getFactory("mybatis.xml").openSession();
    private final VideoMapper videoMapper = sqlSession.getMapper(VideoMapper.class);

    @Test
    public void insert() {
        Video video = new Video();
        video.setVideoTitle("标题");
        video.setAuthor("作者");
        video.setVideoInfo("描述");
        video.setSummaryImage("摘要图.jpg");
        video.setCoverImage("封面图.jpg");
        video.setPrice(1.1D);
        video.setStar(1);
        video.setVideoDeleted(0);
        video.setCreateTime(new Date());
        video.setModifyTime(new Date());
        System.out.println(videoMapper.insert(video));
        System.out.println(video);
    }

    @Test
    public void select() {
        Video condition = new Video();
        condition.setVideoId(34);
        condition.setVideoDeleted(0);
        System.out.println(videoMapper.select(condition));
    }

    @Test
    public void update() {
        Video video = new Video();
        video.setVideoTitle("标题-1");
        video.setAuthor("作者-1");
        Video condition = new Video();
        condition.setVideoId(34);
        condition.setVideoDeleted(0);
        System.out.println(videoMapper.update(video, condition));
    }

    @Test
    public void delete() {
        Video condition = new Video();
        condition.setVideoId(34);
        System.out.println(videoMapper.delete(condition));
    }

    @Test
    public void deleteByVideoIds() {
        Integer[] videoIds = new Integer[]{998, 999};
        System.out.println(videoMapper.deleteByVideoIds(videoIds));
    }

    @After
    public void after() {
        sqlSession.commit();
        sqlSession.close();
    }

}
