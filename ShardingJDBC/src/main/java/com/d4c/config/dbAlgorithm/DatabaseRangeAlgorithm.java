package com.d4c.config.dbAlgorithm;

import org.apache.shardingsphere.api.sharding.standard.RangeShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingValue;

import java.util.Collection;

/**
 * 范围分片
 *
 * @author shuai
 * @since 2023-03-19
 */
public class DatabaseRangeAlgorithm implements RangeShardingAlgorithm<Long> {
    /**
     * 范围分片
     * @param collection 数据源集合
     * @param rangeShardingValue 分片参数
     * @return 直接返回源
     */
    @Override
    public Collection<String> doSharding(Collection<String> collection, RangeShardingValue<Long> rangeShardingValue) {
        return collection;
    }
}


