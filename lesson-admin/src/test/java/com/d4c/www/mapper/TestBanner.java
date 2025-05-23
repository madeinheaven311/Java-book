package com.d4c.www.mapper;

import com.d4c.www.entity.Banner;
import com.d4c.www.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Test;

import java.util.Date;

public class TestBanner {
    private final SqlSession sqlSession = MyBatisUtil.getFactory("mybatis.xml").openSession();
    private final BannerMapper bannerMapper = sqlSession.getMapper(BannerMapper.class);

    @Test
    public void TestInsert() {
        Banner banner = new Banner();
        banner.setBannerUrl("地址");
        banner.setBannerIndex(0);
        banner.setCreateTime(new Date());
        banner.setModifyTime(new Date());
        System.out.println(bannerMapper.insert(banner));
        System.out.println(banner);
        sqlSession.commit();
    }


    @Test
    public void select() {
        Banner banner = new Banner();
        banner.setBannerId(8);
        bannerMapper.select(banner).forEach(System.out::println);
        sqlSession.commit();

    }

    @Test
    public void update() {
        Banner banner = new Banner();
        banner.setBannerUrl("地址-1");
        Banner condition = new Banner();
        condition.setBannerId(8);
        System.out.println(bannerMapper.update(banner, condition));
        sqlSession.commit();

    }



    @Test
    public void delete() {
        Banner condition = new Banner();
        condition.setBannerId(8);
        System.out.println(bannerMapper.delete(condition));
        sqlSession.commit();

    }


    @Test
    public void deleteByBannerIds() {
        Integer[] bannerIds = new Integer[]{997, 998, 999};
        System.out.println(bannerMapper.deleteByBannerIds(bannerIds));
        sqlSession.commit();

    }

    @After
    public void after(){
        // 关闭会话，释放资源
        sqlSession.close();

    }
}
