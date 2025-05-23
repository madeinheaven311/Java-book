package com.d4c.www.component;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import java.io.File;

@Component("我的硬盘空间")
public class MyDiskIndicator implements HealthIndicator {

    /**
     * 端点任务
     *
     * @return 健康实例
     */
    @Override
    public Health health() {

        // 定义盘符总容量和空闲容量
        long totalDiskSpace = 0L, freeDiskSpace = 0L;

        // 展示所有盘符名，如 [C:\, D:\, F:\]
        File[] rootFiles = File.listRoots();

        if (rootFiles != null && rootFiles.length > 0) {

            // 计算计算机中所有盘符的总空间和可用空间
            for (File file : rootFiles) {
                totalDiskSpace += file.getTotalSpace();
                freeDiskSpace += file.getUsableSpace();
            }

            // 返回UP健康信息
            return Health.up()
                    .withDetail("总计容量", totalDiskSpace / 1024 / 1024 / 1024 + "GB")
                    .withDetail("空闲容量", freeDiskSpace / 1024 / 1024 / 1024 + "GB")
                    .build();
        }

        // 返回DOWN健康信息
        return Health.down().build();
    }
}