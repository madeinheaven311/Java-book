package com.d4c.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;

@RestController
public class OO {

    @GetMapping(value = "/OO", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> getSseStream() {
        // 使用Flux生成每秒一个递增的数据流，用于模拟实时数据推送
        return Flux.interval(Duration.ofSeconds(1))
                .map(sequence -> "Data: " + sequence);
    }
}

