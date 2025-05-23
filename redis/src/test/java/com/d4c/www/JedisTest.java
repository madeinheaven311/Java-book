package com.d4c.www;

import org.junit.Test;
import redis.clients.jedis.Jedis;

public class JedisTest {

    @Test
    public void getJedis() {
        Jedis jedis = JedisStandaloneUtil.getJedis();
        System.out.println(jedis.isConnected());
    }

}
