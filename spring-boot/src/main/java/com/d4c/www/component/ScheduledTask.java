package com.d4c.www.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author JoeZhou
 */
@Slf4j
@Component
public class ScheduledTask {

    /** 每天凌晨2点半，执行一次任务 */
    @Scheduled(cron = "0 28 14 * * ?")
    public void cronTask() {
        log.info("cronTask: 执行定时任务");
    }

    /** 立即执行任务，每次任务完成后计时，每隔1秒执行一次任务 */
    //@Scheduled(fixedDelay = 1000)
    public void fixedDelayTask() {
        log.info("fixedDelay: 执行定时任务");
    }

    /** 立即执行任务，每次任务开始前计时，每隔3秒执行一次任务 */
    //@Scheduled(fixedRate = 3000)
    public void fixedRateTask() {
        log.info("fixedRate: 执行定时任务");
    }
}