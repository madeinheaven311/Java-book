package com.d4c.config.dbAlgorithm;

import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;

import java.util.Collection;

public class TablePreciseAlgorithm implements PreciseShardingAlgorithm<Long> {

    @Override
    public String doSharding(Collection<String> availableTargetNames, PreciseShardingValue<Long> shardingValue) {
        // 获取分片键的值
        Long orderId = shardingValue.getValue();
        // 计算目标表的名称
        String targetTable = "t_order_" + (orderId % 2 + 1);

        // 遍历所有可用的表名，找到目标表
        for (String tableName : availableTargetNames) {
            if (tableName.endsWith(targetTable)) {
                return tableName;
            }
        }
        throw new UnsupportedOperationException("表不存在: " + orderId);
    }
}