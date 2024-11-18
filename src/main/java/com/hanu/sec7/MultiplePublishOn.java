package com.hanu.sec7;

import com.hanu.common.Util;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

@Slf4j
public class MultiplePublishOn {
    public static void main(String[] args) {
            var flux = Flux.create(sink -> {
                        for (int i = 1; i < 3; i++) {
                            log.info("Generating {}", i);
                            sink.next(i);
                        }
                    })
                    .publishOn(Schedulers.boundedElastic())
                    .doOnNext(value -> log.info("value {}", value))
                    .doFirst(() -> log.info("first1"))
                    .doFirst(() -> log.info("first2"));
            Runnable runnable = () -> flux.publishOn(Schedulers.parallel())
                    .subscribe(Util.subscriber("sub1"));

            Thread.ofPlatform().start(runnable);

            Util.sleepSeconds(3);
        }
    }
