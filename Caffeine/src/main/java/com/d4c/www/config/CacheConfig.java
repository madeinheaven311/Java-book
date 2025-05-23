package com.d4c.www.config;

import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.cache.support.CompositeCacheManager;
import org.springframework.cache.transaction.TransactionAwareCacheManagerProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConfiguration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Configuration
@Slf4j
@EnableCaching
public class CacheConfig {

    @Value("${spring.redis.host:#{'192.168.216.15'}}")
    private String hostName;

    @Value("${spring.redis.port:#{6379}}")
    private int port;

    @Value("${spring.redis.timeout:#{3000}}")
    private int timeout;

    @Value("${spring.redis.lettuce.pool.max-idle:#{16}}")
    private int maxIdle;

    @Value("${spring.redis.lettuce.pool.min-idle:#{1}}")
    private int minIdle;

    @Value("${spring.redis.lettuce.pool.max-wait:#{16}}")
    private long maxWaitMillis;

    @Value("${spring.redis.lettuce.pool.max-active:#{16}}")
    private int maxActive;

    @Value("${spring.redis.database:#{0}}")
    private int databaseId;

//    @Bean
//    public LettuceConnectionFactory lettuceConnectionFactory() {
//
//        RedisConfiguration redisConfiguration = new RedisStandaloneConfiguration(
//                hostName, port
//        );
//        // 设置选用的数据库号码
//        ((RedisStandaloneConfiguration) redisConfiguration).setDatabase(databaseId);
//
//        // 连接池配置
//        GenericObjectPoolConfig<Object> poolConfig = new GenericObjectPoolConfig<>();
//        poolConfig.setMaxIdle(maxIdle);
//        poolConfig.setMinIdle(minIdle);
//        poolConfig.setMaxTotal(maxActive);
//        poolConfig.setMaxWaitMillis(maxWaitMillis);
//        LettucePoolingClientConfiguration lettucePoolingClientConfiguration
//                = LettucePoolingClientConfiguration.builder()
//                .poolConfig(poolConfig)
//                .commandTimeout(Duration.ofMillis(timeout))
//                .build();
//        // 根据配置和客户端配置创建连接
//        return new LettuceConnectionFactory(redisConfiguration, lettucePoolingClientConfiguration);
//    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate(LettuceConnectionFactory lettuceConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(lettuceConnectionFactory);

        // 使用 FastJsonRedisSerializer 来序列化和反序列化redis 的值
        FastJsonRedisSerializer<Object> serializer = new FastJsonRedisSerializer<>(Object.class);
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setCharset(StandardCharsets.UTF_8);
        serializer.setFastJsonConfig(fastJsonConfig);

        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringRedisSerializer);
        redisTemplate.setHashKeySerializer(stringRedisSerializer);

        redisTemplate.setValueSerializer(serializer);
        redisTemplate.setHashValueSerializer(serializer);

        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }


    @Bean("caffeineCache")
    public Cache<Object,Object> caffeine(){
        return Caffeine.newBuilder()
                .maximumSize(1000)
                .expireAfterWrite(10, TimeUnit.SECONDS)
                .removalListener((key,value,cause) -> {
                    log.info("caffeineCache 键：{},值：{},被清除了，清除原因是：{}",key,value,cause);
                })
                .build();
    }

//
//    @Bean
//    public CacheManager cacheManager(@Autowired LettuceConnectionFactory redisConnectionFactory) {
//
//        // Caffeine本地缓存配置
//        CaffeineCacheManager caffeineCacheManager = new CaffeineCacheManager();
//        caffeineCacheManager.setCaffeine(Caffeine.newBuilder().expireAfterWrite(10, TimeUnit.SECONDS));
//        // 创建支持双写的装饰器
//        List<CacheManager> managers = Arrays.asList(
//                new TransactionAwareCacheManagerProxy(caffeineCacheManager),
//                new TransactionAwareCacheManagerProxy(RedisCacheManager
//                        .builder(redisConnectionFactory)
//                        .cacheDefaults(RedisCacheConfiguration.defaultCacheConfig()
//                                .serializeValuesWith(RedisSerializationContext.SerializationPair
//                                        .fromSerializer(redisTemplate(redisConnectionFactory).getValueSerializer())) // 保持序列化一致
//                                .entryTtl(Duration.ofMinutes(60)))
//                        .build())
//        );
//
//        CompositeCacheManager compositeCacheManager = new CompositeCacheManager();
//        compositeCacheManager.setCacheManagers(managers);
//        compositeCacheManager.setFallbackToNoOpCache(true);
//        return compositeCacheManager;
//    }


}

