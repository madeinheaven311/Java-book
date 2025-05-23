package com.d4c.www.mapper;

import com.d4c.www.entity.Chapter;
import com.d4c.www.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Test;

import java.util.Date;

public class TestChapter {
    private final SqlSession sqlSession = MyBatisUtil.getFactory("mybatis.xml").openSession();
    private final ChapterMapper chapterMapper = sqlSession.getMapper(ChapterMapper.class);

    @Test
    public void insert() {
        Chapter chapter = new Chapter();
        chapter.setChapterTitle("标题");
        chapter.setChapterInfo("描述");
        chapter.setFkVideoId(1);
        chapter.setChapterIndex(999);
        chapter.setCreateTime(new Date());
        chapter.setModifyTime(new Date());
        System.out.println(chapterMapper.insert(chapter));
        System.out.println(chapter);
    }

    @Test
    public void select() {
        Chapter condition = new Chapter();
        condition.setChapterId(56);
        System.out.println(chapterMapper.select(condition));
    }

    @Test
    public void update() {
        Chapter chapter = new Chapter();
        chapter.setChapterTitle("标题-1");
        chapter.setChapterInfo("描述-1");
        Chapter condition = new Chapter();
        condition.setChapterId(56);
        System.out.println(chapterMapper.update(chapter, condition));
    }

    @Test
    public void delete() {
        Chapter condition = new Chapter();
        condition.setChapterId(56);
        System.out.println(chapterMapper.delete(condition));
    }

    @Test
    public void deleteByChapterIds() {
        Integer[] chapterIds = new Integer[]{998, 999};
        System.out.println(chapterMapper.deleteByChapterIds(chapterIds));
    }

    @After
    public void after() {
        sqlSession.commit();
        sqlSession.close();
    }
}
