package com.d4c.www;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author JoeZhou
 */
public class JedisStandaloneUtil {

    /** Jedis连接池 */
    private static final JedisPool jedisPool;
    /** Jedis超时时间 */
    private static final int TIMEOUT = 10000;
    /** Redis主机地址 */
    private static final String HOST = "192.168.216.4";
    /** Redis端口号 */
    private static final int PORT = 6379;
    /** Jedis连接池最大连接 */
    private static final int MAX_TOTAL = 8;
    /** Jedis连接池最大阻塞时间 */
    private static final long MAX_WAIT_MILLIS = 10000L;
    /** Jedis连接池最大空闲 */
    private static final int MAX_IDLE = 8;
    /** Jedis连接池最小空闲 */
    private static final int MIN_IDLE = 0;

    static {
        // 初始化Jedis连接池配置实例: 最大连接，最大阻塞时间，最大空闲，最小空闲
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(MAX_TOTAL);
        poolConfig.setMaxWaitMillis(MAX_WAIT_MILLIS);
        poolConfig.setMaxIdle(MAX_IDLE);
        poolConfig.setMinIdle(MIN_IDLE);
        // 初始化Jedis单机连接池实例
        jedisPool = new JedisPool(poolConfig, HOST, PORT, TIMEOUT);
    }
    /**
     * 从Jedis连接池中获取并返回一个Jedis连接
     *
     * @return 一个Jedis连接
     */
    public static Jedis getJedis() {
        return jedisPool.getResource();
    }
}