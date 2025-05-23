package com.d4c.config.dbAlgorithm;

import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;

import java.util.Collection;

/**
 * 精确分片
 *
 * @author shuai
 * @since 2023-03-19
 */
public class DatabasePreciseAlgorithm implements PreciseShardingAlgorithm<Long> {
    /**
     * 精确分片
     * @param collection 数据源集合
     * @param preciseShardingValue 分片参数
     * @return 数据库
     */
    @Override
    public String doSharding(Collection<String> collection, PreciseShardingValue<Long> preciseShardingValue) {
        //分片键的值
        Long value = preciseShardingValue.getValue();
        String dbName = "m" + (value % 2 + 1);
        if(!collection.contains(dbName)){
           throw new UnsupportedOperationException("数据源"+ dbName + "不存在");
        }
        return dbName;
    }
}


