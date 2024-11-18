package com.hanu.sec08;

import com.hanu.common.Util;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
@Slf4j
public class BufferStrategy {
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
//                .onBackpressureBuffer()
                .onBackpressureBuffer(10) //fixed buffer strategy if queue is already filled with 10 items and again going to push other items means it will throw exception. atleast 75% should be free
                .limitRate(1)
                .publishOn(Schedulers.boundedElastic())
                .map(BufferStrategy::process)
                .subscribe();
    }

    private static int process(int i) {
        log.info("Received {}", i);
        Util.sleepSeconds(1);
        return i;
    }
}
