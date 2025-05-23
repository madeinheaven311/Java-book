package com.d4c.www.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.TimeUnit;

/** @author JoeZhou */
@Component
public class RedisUtil {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 获取 StringRedisTemplate 对象
     *
     * @return StringRedisTemplate 对象
     */
    public StringRedisTemplate getStringRedisTemplate() {
        return this.stringRedisTemplate;
    }

    /**
     * COMMON: 按缓存名单删一条缓存记录
     *
     * @param key 缓存名
     */
    public void del(String key) {
        stringRedisTemplate.delete(key);
    }

    /**
     * COMMON: 按缓存名的集合批删多条缓存记录
     *
     * @param keys 缓存名的集合
     */
    public void del(Set<String> keys) {
        stringRedisTemplate.delete(keys);
    }

    /**
     * COMMON: 按缓存名查询该缓存记录是否存在
     *
     * @param key 缓存名
     * @return true表示缓存记录存在，false表示缓存记录不存在
     */
    public boolean exists(String key) {
        return Boolean.TRUE.equals(stringRedisTemplate.hasKey(key));
    }

    /**
     * STRING: 永久存储一条字符串类型的缓存记录
     *
     * @param key 缓存名
     * @param val 字符串类型的缓存记录
     */
    public void set(String key, String val) {
        stringRedisTemplate.opsForValue().set(key, val);
    }

    /**
     * STRING: 永久存储一条字符串类型的缓存记录，仅在缓存名不存在时才会成功
     *
     * @param key 缓存名
     * @param val 字符串类型的缓存记录
     * @return true表示存储成功，false表示存储失败
     */
    public boolean setNx(String key, String val) {
        return Boolean.TRUE.equals(stringRedisTemplate.opsForValue().setIfAbsent(key, val));
    }

    /**
     * STRING: 带期限存储一条字符串类型的缓存记录
     *
     * @param key     缓存名
     * @param val     字符串类型的缓存记录
     * @param timeout 缓存过期时间
     * @param unit    缓存过期时间单位，如 TimeUnit.MILLISECONDS 等
     */
    public void setEx(String key, String val, long timeout, TimeUnit unit) {
        stringRedisTemplate.opsForValue().set(key, val, timeout, unit);
    }

    /**
     * STRING: 按缓存名查询一条字符串类型的缓存记录
     *
     * @param key 缓存名
     * @return 指定key的缓存数据
     */
    public String get(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    /**
     * STRING: 按缓存名查询一条字符串类型的缓存记录的字节长度
     *
     * @param key 缓存名
     * @return 缓存记录的字节长度
     */
    public long strLen(String key) {
        Long result = stringRedisTemplate.opsForValue().size(key);
        assert result != null;
        return result;
    }

    /**
     * STRING: 按缓存名自增一条字符串类型的缓存记录
     *
     * @param key       缓存名
     * @param increment 自增整数，负数表示自减
     * @return 自增后的结果
     */
    public long incr(String key, long increment) {
        Long result = stringRedisTemplate.opsForValue().increment(key, increment);
        assert result != null;
        return result;
    }

    /**
     * STRING: 按缓存名自增一条字符串类型的缓存记录
     *
     * @param key       缓存名
     * @param increment 自增浮点数，负数表示自减
     * @return 自增后的结果
     */
    public double incr(String key, double increment) {
        Double result = stringRedisTemplate.opsForValue().increment(key, increment);
        assert result != null;
        return result;
    }

    /**
     * STRING: 按缓存名的集合批删查询多条字符串类型的缓存记录
     *
     * @param keys 缓存名的集合
     * @return 多条字符串类型的缓存记录
     */
    public List<String> mGet(Set<String> keys) {
        return stringRedisTemplate.opsForValue().multiGet(keys);
    }

    /**
     * STRING: 批删设置多条字符串类型的缓存记录
     *
     * @param map 格式为 {缓存名, 缓存记录}
     */
    public void mSet(Map<String, String> map) {
        stringRedisTemplate.opsForValue().multiSet(map);
    }

    /**
     * HASH: 向指定hash容器添加一条属性
     *
     * @param key   hash容器名，容器自动创建与销毁
     * @param field 属性名
     * @param val   属性值
     */
    public void hSet(String key, String field, String val) {
        stringRedisTemplate.opsForHash().put(key, field, val);
    }

    /**
     * HASH: 向指定hash容器添加一条属性，仅在容器名不存在时才会成功
     *
     * @param key   hash容器名，容器自动创建与销毁
     * @param field 属性名
     * @param val   属性值
     * @return true表示添加成功，false表示添加失败
     */
    public boolean hSetNx(String key, String field, String val) {
        return stringRedisTemplate.opsForHash().putIfAbsent(key, field, val);
    }

    /**
     * HASH: 返回指定hash容器中是否存在指定属性
     *
     * @param key   hash容器名，容器自动创建与销毁
     * @param field 属性名
     * @return true表示属性存在，false表示属性不存在
     */
    public boolean hExists(String key, String field) {
        return stringRedisTemplate.opsForHash().hasKey(key, field);
    }

    /**
     * HASH: 返回指定hash容器中的指定属性
     *
     * @param key   hash容器名，容器自动创建与销毁
     * @param field 属性名
     * @return 属性值
     */
    public Object hGet(String key, String field) {
        return stringRedisTemplate.opsForHash().get(key, field);
    }

    /**
     * HASH: 返回指定hash容器中的全部属性以及对应的属性值
     *
     * @param key hash容器名，容器自动创建与销毁
     * @return 全部属性以及对应的属性值
     */
    public Map<Object, Object> hGetAll(String key) {
        return stringRedisTemplate.opsForHash().entries(key);
    }

    /**
     * HASH: 返回指定hash容器中的全部属性集合
     *
     * @param key hash容器名，容器自动创建与销毁
     * @return 全部属性集合
     */
    public Set<Object> hKeys(String key) {
        return stringRedisTemplate.opsForHash().keys(key);
    }

    /**
     * HASH: 返回指定hash容器中的全部属性值列表
     *
     * @param key hash容器名，容器自动创建与销毁
     * @return 全部属性值列表
     */
    public List<Object> hVals(String key) {
        return stringRedisTemplate.opsForHash().values(key);
    }

    /**
     * HASH: 对指定hash容器中的指定属性进行自增
     *
     * @param key       hash容器名，容器自动创建与销毁
     * @param field     属性名称
     * @param increment 自增整数，负数表示自减
     * @return 自增后的结果
     */
    public long hIncrBy(String key, Object field, long increment) {
        return stringRedisTemplate.opsForHash().increment(key, field, increment);
    }

    /**
     * HASH: 对指定hash容器中的指定属性进行自增
     *
     * @param key       hash容器名，容器自动创建与销毁
     * @param field     属性名称
     * @param increment 自增浮点数，负数表示自减
     * @return 自增后的结果
     */
    public double hIncrBy(String key, Object field, double increment) {
        return stringRedisTemplate.opsForHash().increment(key, field, increment);
    }

    /**
     * HASH: 返回指定hash容器中的属性个数
     *
     * @param key hash容器名，容器自动创建与销毁
     * @return 属性个数
     */
    public long hLen(String key) {
        return stringRedisTemplate.opsForHash().size(key);
    }

    /**
     * HASH: 从指定hash容器中，按指定的属性数组批量删除属性
     *
     * @param key    hash容器名，容器自动创建与销毁
     * @param fields 属性数组
     * @return 影响条目数
     */
    public long hDelete(String key, Object... fields) {
        return stringRedisTemplate.opsForHash().delete(key, fields);
    }

    /**
     * LIST: 在指定list容器的头部插入一个元素
     *
     * @param key list容器名，容器自动创建与销毁
     * @param val 元素
     * @return 返回插入元素后的容器长度
     */
    public long lPush(String key, String val) {
        Long result = stringRedisTemplate.opsForList().leftPush(key, val);
        assert result != null;
        return result;
    }

    /**
     * LIST: 在指定list容器的头部插入多个元素
     *
     * @param key  list容器名，容器自动创建与销毁
     * @param vals 元素数组
     * @return 返回插入元素后的容器长度
     */
    public Long lPush(String key, String... vals) {
        return stringRedisTemplate.opsForList().leftPushAll(key, vals);
    }

    /**
     * LIST: 在指定list容器的指定支点元素前插入指定元素
     *
     * @param key   list容器名，容器自动创建与销毁
     * @param pivot 支点元素
     * @param val   元素
     * @return 返回插入元素后的容器长度
     */
    public Long lInsertBefore(String key, String pivot, String val) {
        return stringRedisTemplate.opsForList().leftPush(key, pivot, val);
    }

    /**
     * LIST: 在指定list容器的指定支点元素后插入指定元素
     *
     * @param key   list容器名，容器自动创建与销毁
     * @param pivot 支点元素
     * @param val   元素
     * @return 返回插入元素后的容器长度
     */
    public Long lInsertAfter(String key, String pivot, String val) {
        return stringRedisTemplate.opsForList().rightPush(key, pivot, val);
    }

    /**
     * LIST: 在指定list容器的尾部插入一个元素
     *
     * @param key list容器名，容器自动创建与销毁
     * @param val 元素
     * @return 返回插入元素后的容器长度
     */
    public Long rPush(String key, String val) {
        return stringRedisTemplate.opsForList().rightPush(key, val);
    }

    /**
     * LIST: 在指定list容器的尾部插入多个元素
     *
     * @param key  list容器名，容器自动创建与销毁
     * @param vals 元素数组
     * @return 返回插入元素后的容器长度
     */
    public Long rPush(String key, String... vals) {
        return stringRedisTemplate.opsForList().rightPushAll(key, vals);
    }

    /**
     * LIST: 返回指定list容器中的元素个数
     *
     * @param key list容器名，容器自动创建与销毁
     * @return list容器中的元素个数
     */
    public Long lLen(String key) {
        return stringRedisTemplate.opsForList().size(key);
    }

    /**
     * LIST: 返回指定list容器中指定范围内的全部元素
     *
     * @param key   list容器名，容器自动创建与销毁
     * @param start 开始位置，索引从0开始
     * @param end   结束位置，-1表示最后位置
     * @return list容器中指定范围内的全部元素
     */
    public List<String> lRange(String key, long start, long end) {
        return stringRedisTemplate.opsForList().range(key, start, end);
    }

    /**
     * LIST: 弹出指定list容器中的头部元素
     *
     * @param key list容器名，容器自动创建与销毁
     * @return 弹出的元素
     */
    public String lPop(String key) {
        return stringRedisTemplate.opsForList().leftPop(key);
    }

    /**
     * LIST: 弹出指定list容器中的尾部元素
     *
     * @param key list容器名，容器自动创建与销毁
     * @return 弹出的元素
     */
    public String rPop(String key) {
        return stringRedisTemplate.opsForList().rightPop(key);
    }

    /**
     * LIST: 通过索引返回list容器中的指定元素
     *
     * @param key   list容器名，容器自动创建与销毁
     * @param index 索引，从0开始
     * @return 指定索引位置上的元素
     */
    public String lIndex(String key, long index) {
        return stringRedisTemplate.opsForList().index(key, index);
    }

    /**
     * LIST: 通过索引设置list容器中的指定元素
     *
     * @param key   list容器名，容器自动创建与销毁
     * @param index 索引，从0开始
     * @param val   新值
     */
    public void lSet(String key, long index, String val) {
        stringRedisTemplate.opsForList().set(key, index, val);
    }

    /**
     * SET: 向指定set容器中添加多个元素
     *
     * @param key  set容器名，容器自动创建与销毁
     * @param vals 元素数组
     * @return 影响条目数
     */
    public long sAdd(String key, String... vals) {
        Long result = stringRedisTemplate.opsForSet().add(key, vals);
        assert result != null;
        return result;
    }

    /**
     * SET: 返回指定set容器中的元素个数
     *
     * @param key set容器名，容器自动创建与销毁
     * @return set容器中的元素个数
     */
    public Long sCard(String key) {
        return stringRedisTemplate.opsForSet().size(key);
    }

    /**
     * SET: 返回指定set容器中的全部元素，重量级命令
     *
     * @param key set容器名，容器自动创建与销毁
     * @return set容器中的全部元素
     */
    public Set<String> sMembers(String key) {
        return stringRedisTemplate.opsForSet().members(key);
    }

    /**
     * SET: 从指定set容器中删除多个元素
     *
     * @param key  set容器名，容器自动创建与销毁
     * @param vals 元素数组
     * @return 影响条目数
     */
    public long sRem(String key, Object... vals) {
        Long result = stringRedisTemplate.opsForSet().remove(key, vals);
        assert result != null;
        return result;
    }

    /**
     * SET: 返回指定set容器中是否包含指定元素
     *
     * @param key set容器名，容器自动创建与销毁
     * @param val 元素
     * @return true表示包含指定元素，false表示不包含指定元素
     */
    public boolean sIsMember(String key, Object val) {
        Boolean result = stringRedisTemplate.opsForSet().isMember(key, val);
        assert result != null;
        return result;
    }

    /**
     * SET: 随机返回指定set容器中指定数量的元素
     *
     * @param key   set容器名，容器自动创建与销毁
     * @param count 指定数量，负数表示结果不重复，正数表示结果可重复
     * @return 随机元素列表
     */
    public Collection<String> sRandomMember(String key, long count) {
        if (count < 0) {
            count = Math.abs(count);
            return stringRedisTemplate.opsForSet().distinctRandomMembers(key, count);
        } else if (count > 0) {
            return stringRedisTemplate.opsForSet().randomMembers(key, count);
        } else {
            return new ArrayList<>();
        }
    }

    /**
     * SET: 随机弹出指定set容器中指定数量的元素
     *
     * @param key   set容器名，容器自动创建与销毁
     * @param count 指定数量，负数表示结果不重复，正数表示结果可重复
     * @return 随机元素列表
     */
    public List<String> sPop(String key, long count) {
        return stringRedisTemplate.opsForSet().pop(key, count);
    }

    /**
     * SET: 计算两个set容器的差集并存储到目标set容器中
     *
     * @param key01 操作容器01
     * @param key02 操作容器02
     * @param key03 目标set容器
     * @return 影响条目数
     */
    public long sDiffStore(String key01, String key02, String key03) {
        Long result = stringRedisTemplate.opsForSet().differenceAndStore(key01, key02, key03);
        assert result != null;
        return result;
    }

    /**
     * SET: 计算两个set容器的交集并存储到目标set容器中
     *
     * @param key01 操作容器01
     * @param key02 操作容器02
     * @param key03 目标set容器
     * @return 影响条目数
     */
    public long sInterStore(String key01, String key02, String key03) {
        Long result = stringRedisTemplate.opsForSet().intersectAndStore(key01, key02, key03);
        assert result != null;
        return result;
    }

    /**
     * SET: 计算两个set容器的并集并存储到目标set容器中
     *
     * @param key01 操作容器01
     * @param key02 操作容器02
     * @param key03 目标set容器
     * @return 影响条目数
     */
    public long sUnionStore(String key01, String key02, String key03) {
        Long result = stringRedisTemplate.opsForSet().unionAndStore(key01, key02, key03);
        assert result != null;
        return result;
    }

    /**
     * ZSET: 向指定zset容器中添加元素
     *
     * @param key   zset容器名，容器自动创建与销毁
     * @param val   元素
     * @param score 元素得分
     * @return 影响条目数
     */
    public boolean zAdd(String key, String val, double score) {
        Boolean result = stringRedisTemplate.opsForZSet().add(key, val, score);
        assert result != null;
        return result;
    }

    /**
     * ZSET: 返回指定zset容器中的元素个数
     *
     * @param key zset容器名，容器自动创建与销毁
     * @return zset容器中的元素个数
     */
    public Long zCard(String key) {
        return stringRedisTemplate.opsForZSet().zCard(key);
    }

    /**
     * ZSET: 返回指定zset容器中指定分数范围内的元素个数
     *
     * @param key zset容器名，容器自动创建与销毁
     * @param min 最小分数
     * @param max 最大分数
     * @return 指定分数范围内的元素个数
     */
    public Long zCount(String key, double min, double max) {
        return stringRedisTemplate.opsForZSet().count(key, min, max);
    }

    /**
     * ZSET: 返回指定zset容器中指定元素的分数
     *
     * @param key zset容器名，容器自动创建与销毁
     * @param val 元素
     * @return 指定元素的分数
     */
    public Double zScore(String key, Object val) {
        return stringRedisTemplate.opsForZSet().score(key, val);
    }

    /**
     * ZSET: 升序返回指定zset容器中指定索引范围内的元素
     *
     * @param key   zset容器名，容器自动创建与销毁
     * @param start 索引起始位置，从0开始
     * @param end   索引结束位置，-1表示最后
     * @return 指定索引范围内的元素
     */
    public Set<String> zRange(String key, long start, long end) {
        return stringRedisTemplate.opsForZSet().range(key, start, end);
    }

    /**
     * ZSET: 降序返回指定zset容器中指定索引范围内的元素
     *
     * @param key   zset容器名，容器自动创建与销毁
     * @param start 索引起始位置，从0开始
     * @param end   索引结束位置，-1表示最后
     * @return 指定索引范围内的元素
     */
    public Set<String> zRevRange(String key, long start, long end) {
        return stringRedisTemplate.opsForZSet().reverseRange(key, start, end);
    }

    /**
     * ZSET: 带分数升序返回指定zset容器中指定索引范围内的元素
     *
     * @param key   zset容器名，容器自动创建与销毁
     * @param start 索引起始位置，从0开始
     * @param end   索引结束位置，-1表示最后
     * @return 指定索引范围内的元素
     */
    public Set<ZSetOperations.TypedTuple<String>> zRangeWithScores(
            String key, long start, long end) {
        return stringRedisTemplate.opsForZSet().rangeWithScores(key, start, end);
    }

    /**
     * ZSET: 带分数降序返回指定zset容器中指定索引范围内的元素
     *
     * @param key   zset容器名，容器自动创建与销毁
     * @param start 索引起始位置，从0开始
     * @param end   索引结束位置，-1表示最后
     * @return 指定索引范围内的元素
     */
    public Set<ZSetOperations.TypedTuple<String>> zRevRangeWithScores(
            String key, long start, long end) {
        return stringRedisTemplate.opsForZSet().reverseRangeWithScores(key, start, end);
    }

    /**
     * ZSET: 升序返回指定zset容器中指定分数范围内的元素
     *
     * @param key zset容器名，容器自动创建与销毁
     * @param min 最小分数
     * @param max 最大分数
     * @return 指定分数范围内的元素
     */
    public Set<String> zRangeByScore(String key, double min, double max) {
        return stringRedisTemplate.opsForZSet().rangeByScore(key, min, max);
    }

    /**
     * ZSET: 降序返回指定zset容器中指定分数范围内的元素
     *
     * @param key zset容器名，容器自动创建与销毁
     * @param min 最小分数
     * @param max 最大分数
     * @return 指定分数范围内的元素
     */
    public Set<String> zRevRangeByScore(String key, double min, double max) {
        return stringRedisTemplate.opsForZSet().reverseRangeByScore(key, min, max);
    }

    /**
     * ZSET: 带分数升序返回指定zset容器中指定分数范围内的元素
     *
     * @param key zset容器名，容器自动创建与销毁
     * @param min 最小分数
     * @param max 最大分数
     * @return 指定分数范围内的元素
     */
    public Set<ZSetOperations.TypedTuple<String>> zRangeByScoreWithScores(
            String key, double min, double max) {
        return stringRedisTemplate.opsForZSet().rangeByScoreWithScores(key, min, max);
    }

    /**
     * ZSET: 带分数降序返回指定zset容器中指定分数范围内的元素
     *
     * @param key zset容器名，容器自动创建与销毁
     * @param min 最小分数
     * @param max 最大分数
     * @return 指定分数范围内的元素
     */
    public Set<ZSetOperations.TypedTuple<String>> zRevRangeByScoreWithScores(
            String key, double min, double max) {
        return stringRedisTemplate.opsForZSet().reverseRangeByScoreWithScores(key, min, max);
    }

    /**
     * ZSET: 返回指定zset容器中指定元素的升序排名
     *
     * @param key zset容器名，容器自动创建与销毁
     * @param val 元素
     * @return 指定元素的升序排名，从0开始
     */
    public Long zRank(String key, Object val) {
        return stringRedisTemplate.opsForZSet().rank(key, val);
    }

    /**
     * ZSET: 返回指定zset容器中指定元素的降序排名
     *
     * @param key zset容器名，容器自动创建与销毁
     * @param val 元素
     * @return 指定元素的降序排名，从0开始
     */
    public Long zRevRank(String key, Object val) {
        return stringRedisTemplate.opsForZSet().reverseRank(key, val);
    }

    /**
     * ZSET: 从指定zset容器中批量删除多个元素
     *
     * @param key  zset容器名，容器自动创建与销毁
     * @param vals 元素数组
     * @return 影响条目数
     */
    public Long zRem(String key, Object... vals) {
        return stringRedisTemplate.opsForZSet().remove(key, vals);
    }

    /**
     * ZSET: 对指定zset容器中的指定元素自增指定分数
     *
     * @param key       zset容器名，容器自动创建与销毁
     * @param val       元素
     * @param increment 自增浮点型分数，负数表示自减
     * @return 自增后的分数
     */
    public double zIncrBy(String key, String val, double increment) {
        Double result = stringRedisTemplate.opsForZSet().incrementScore(key, val, increment);
        assert result != null;
        return result;
    }
}