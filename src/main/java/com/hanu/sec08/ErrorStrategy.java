package com.hanu.sec08;

import com.hanu.common.Util;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
@Slf4j
public class ErrorStrategy {
    public static void main(String[] args) {
        Flux<Integer> flux = Flux.create(sink -> {
                    for (int i = 1; i <= 500 && !sink.isCancelled(); i++) {
                        log.info("Generating {}", i);
                        sink.next(i);
                        Util.sleepSeconds(Duration.ofMillis(50));
                    }
                })
                .cast(Integer.class)
                .subscribeOn(Schedulers.newParallel("hanu"));

        flux
                .onBackpressureError()
                .limitRate(1)
                .publishOn(Schedulers.boundedElastic())
                .map(ErrorStrategy::process)
                .subscribe();
    }
    private static int process(int i) {
        log.info("Received {}", i);
        Util.sleepSeconds(1);
        return i;
    }
}
