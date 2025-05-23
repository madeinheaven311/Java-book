package com.d4c.www.mapper;

import com.d4c.www.entity.VideoOrder;
import com.d4c.www.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Test;

import java.util.Date;

public class TestVideoOrder {
    private final SqlSession sqlSession = MyBatisUtil.getFactory("mybatis.xml").openSession();
    private final VideoOrderMapper videoOrderMapper = sqlSession.getMapper(VideoOrderMapper.class);

    @Test
    public void insert() {
        VideoOrder videoOrder = new VideoOrder();
        videoOrder.setFkOrderId(1);
        videoOrder.setFkVideoId(2);
        videoOrder.setFkUserId(3);
        videoOrder.setCreateTime(new Date());
        videoOrder.setModifyTime(new Date());
        System.out.println(videoOrderMapper.insert(videoOrder));
        System.out.println(videoOrder);
    }

    @Test
    public void select() {
        VideoOrder condition = new VideoOrder();
        condition.setVideoOrderId(2);
        videoOrderMapper.select(condition).forEach(videoOrder -> {
            System.out.println("videoOrder: " + videoOrder);
            System.out.println("user: " + videoOrder.getUser());
            System.out.println("order: " + videoOrder.getOrder());
            System.out.println("video: " + videoOrder.getVideo());
        });
    }

    @Test
    public void update() {
        VideoOrder videoOrder = new VideoOrder();
        videoOrder.setFkOrderId(11);
        videoOrder.setFkVideoId(12);
        VideoOrder condition = new VideoOrder();
        condition.setVideoOrderId(32);
        System.out.println(videoOrderMapper.update(videoOrder, condition));
    }

    @Test
    public void delete() {
        VideoOrder condition = new VideoOrder();
        condition.setVideoOrderId(1);
        System.out.println(videoOrderMapper.delete(condition));
    }

    @Test
    public void deleteByVideoOrderIds() {
        Integer[] videoOrderIds = new Integer[]{998, 999};
        System.out.println(videoOrderMapper.deleteByVideoOrderIds(videoOrderIds));
    }

    @After
    public void after() {
        sqlSession.commit();
        sqlSession.close();
    }
}
