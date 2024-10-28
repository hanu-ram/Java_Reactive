package com.hanu.sec5;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
public class Subscribe {
    public static void main(String[] args) {
        Flux.range(1, 10)
                .map(i -> {
                    if (i == 5) {
                        return i/0 ;
                    } else {
                        return i;
                    }
                })
                .doOnNext(i -> log.info("Received {}", i))
                .doOnComplete(() -> log.info("Completed successfully"))
                .doOnError(e -> log.error("Failed to consume {}", e.getMessage()))
                .subscribe(); //without subscriber also we can use doOnNext, doOnComplete, and doOnError for doing similar kind of work
    }
}
