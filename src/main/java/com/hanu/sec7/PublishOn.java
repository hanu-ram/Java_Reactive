package com.hanu.sec7;

import com.hanu.common.Util;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

/**
 * Publish on is mainly used for consumer side or subscriber side.
 * Closest to subscriber will take the precedence and its downstream and subscribe on is upstream
 * If you want to control the flow that you want to process in your own separate thread then we can use publish on.
 */
@Slf4j
public class PublishOn {
    public static void main(String[] args) {
        var flux = Flux.create(sink -> {
                    for (int i = 1; i < 3; i++) {
                        log.info("Generating {}", i);
                        sink.next(i);
                    }
                }).doOnNext(value -> log.info("value {}", value))
                .doFirst(() -> log.info("first1"))
                .subscribeOn(Schedulers.boundedElastic())
                .doFirst(() -> log.info("first2"));
        Runnable runnable = () -> flux.publishOn(Schedulers.parallel())
                .subscribe(Util.subscriber("sub1"));

        Thread.ofPlatform().start(runnable);

        Util.sleepSeconds(3);
    }
}
