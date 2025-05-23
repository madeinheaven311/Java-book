package com.d4c.www;

import com.d4c.www.util.RedisUtil;
import lombok.SneakyThrows;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

/** @author JoeZhou */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RedisApp.class)
public class StringRedisTemplateTest {

    @Autowired
    RedisUtil redisUtil = new RedisUtil();


    @SneakyThrows
    @Test
    public void string() {
        // set/setex/mSet/setNx/get/strLen/incr/mGet
        redisUtil.set("cccc", "zhaosi");
        redisUtil.setEx("aaaa", "bbbb", 10, TimeUnit.SECONDS);
        redisUtil.setNx("name", "liuneng");
        redisUtil.setNxEX("name", "liuneng", 10, TimeUnit.SECONDS);
        String result = redisUtil.get("name");
        System.out.println(result);
        redisUtil.strLen("name");
    }

    @Test
    public void common() {
        // set/del/exists
    }

    @Test
    public void hash() {
        // hSet/hSetNx/hExists/hGet/hGetAll/hKeys/hVals/hIncryBy/hLen/hDelete

    }

    @Test
    public void list() {
        // lPush/lInsertBefore/lInsertAfter/rPush/lLen/lRange/lPop/rPop/lIndex/lSet/lRange
    }

    public void set() {
        // sAdd/sCard/sMembers/sRem/sIsMember/sRandomMember/sPop/sDiffStore/
        // sInterStore/sUnionStore//
    }

    @Test
    public void zset() {
        // zAdd/zCard/zCount/zRange/zScore/zRevRange/zRangeByScore/zRevRangeByScore/zIncrBy/zRem

    }

}