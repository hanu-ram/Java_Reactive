package com.hanu.sec08;

import com.hanu.common.Util;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

@Slf4j
public class FluxLatestStrategy {
    public static void main(String[] args) {
        Flux<Integer> flux = Flux.create(sink -> {
                    for (int i = 1; i <= 500 && !sink.isCancelled(); i++) {
                        log.info("Generating {}", i);
                        sink.next(i);
                        Util.sleepSeconds(Duration.ofMillis(50));
                    }
                    sink.complete();
                })
                .cast(Integer.class)
                .subscribeOn(Schedulers.newParallel("hanu"));

        flux
//                .onBackpressureDrop() //it will control the flow based on the request and drop rest of the pushed items.
                .onBackpressureLatest() //90% similar as drop(). But, it will hold the latest pushed item and drop rest and whenever subscriber processed and request for new then the hold item will be served.
                .log()
                .limitRate(1)
                .publishOn(Schedulers.boundedElastic())
                .map(FluxLatestStrategy::process)
                .subscribe();
    }

    private static int process(int i) {
        log.info("Received {}", i);
        Util.sleepSeconds(1);
        return i;
    }
}
