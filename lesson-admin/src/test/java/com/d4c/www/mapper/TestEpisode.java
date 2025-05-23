package com.d4c.www.mapper;

import com.d4c.www.entity.Episode;
import com.d4c.www.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Test;

import java.util.Date;

public class TestEpisode {
    private final SqlSession sqlSession = MyBatisUtil.getFactory("mybatis.xml").openSession();
    private final EpisodeMapper episodeMapper = sqlSession.getMapper(EpisodeMapper.class);

    @Test
    public void insert() {
        Episode episode = new Episode();
        episode.setEpisodeTitle("标题");
        episode.setEpisodeInfo("描述");
        episode.setEpisodeUrl("地址");
        episode.setFkChapterId(1);
        episode.setEpisodeIndex(999);
        episode.setCreateTime(new Date());
        episode.setModifyTime(new Date());
        System.out.println(episodeMapper.insert(episode));
        System.out.println(episode);
    }

    @Test
    public void select() {
        Episode condition = new Episode();
        condition.setEpisodeId(126);
        episodeMapper.select(condition).forEach(System.out::println);
    }

    @Test
    public void update() {
        Episode episode = new Episode();
        episode.setEpisodeTitle("标题-1");
        episode.setEpisodeInfo("描述-1");
        Episode condition = new Episode();
        condition.setEpisodeId(126);
        System.out.println(episodeMapper.update(episode, condition));
    }

    @Test
    public void delete() {
        Episode condition = new Episode();
        condition.setEpisodeId(126);
        System.out.println(episodeMapper.delete(condition));
    }

    @Test
    public void deleteByEpisodeIds() {
        Integer[] episodeIds = new Integer[]{998, 999};
        System.out.println(episodeMapper.deleteByEpisodeIds(episodeIds));
    }

    @After
    public void after() {
        sqlSession.commit();
        sqlSession.close();
    }
}
