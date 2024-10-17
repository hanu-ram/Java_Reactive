package com.hanu.sec2;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
public class MonoErrorConsumer {
    public static void main(String[] args) {
        var monoError = Mono.error(new IllegalArgumentException("Something went wrong"));
        monoError.subscribe(s -> log.info("executed"), err -> log.error(err.getMessage()));
    }
}
