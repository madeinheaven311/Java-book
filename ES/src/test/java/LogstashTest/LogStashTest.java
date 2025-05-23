package LogstashTest;

import com.test.www.EsApp;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

/** @author JoeZhou */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = EsApp.class)
public class LogStashTest {  
  
    @SneakyThrows
    @Test
    public void logstash() {  
        log.debug("一条DEBUG级别日志测试记录");  
        log.info("一条INFO级别日志测试记录");  
        log.warn("一条WARN级别日志测试记录");  
        log.error("一条ERROR级别日志测试记录");  
        // 保证Logstash在项目运行结束前完成日志采集工作
        TimeUnit.SECONDS.sleep(2L);
    }  
}