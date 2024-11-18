package com.hanu.sec08;

import com.hanu.common.Util;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

@Slf4j
public class FluxCreateProblem {
    public static void main(String[] args) {
        System.setProperty("reactor.bufferSize.small", "16");
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
                .publishOn(Schedulers.boundedElastic())
                .map(FluxCreateProblem::process)
                .subscribe();
    }

    private static int process(int i) {
        log.info("Received {}", i);
        Util.sleepSeconds(1);
        return i;
    }
}
