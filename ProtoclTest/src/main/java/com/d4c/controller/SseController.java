package com.d4c.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController("/api/v1")
public class SseController {

    private ExecutorService nonBlockingService = Executors.newCachedThreadPool();

    @GetMapping("/sse")
    public SseEmitter getSseStream() {
        SseEmitter emitter = new SseEmitter();

        nonBlockingService.execute(() -> {
            // 这里模拟数据发送给客户端的逻辑
            try {
                for (int i = 0; i < 10; i++) {
                    emitter.send("Data: " + i);
                    Thread.sleep(1000);
                }
                emitter.complete();
            } catch (Exception ex) {
                emitter.completeWithError(ex);
            }
        });

        return emitter;
    }

    @GetMapping("/test")
    public String getSseStreamWithTimeout() throws InterruptedException {
        Thread.sleep(10000);
        return "Test";
    }
}
