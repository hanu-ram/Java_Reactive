package com.hanu.sec7;

import com.hanu.common.Util;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

/**
 * immediate() is also a method from scheduler where if you want to run the reactive pipeline
 * with the current thread, then we can use immediate()
 */
@Slf4j
public class Immediate {
    public static void main(String[] args) {
        Flux<Object> objectFlux = Flux.create(fluxSink -> {
            for (int i = 1; i <= 3; i++) {
                log.info("{}", fluxSink.next(i));
            }
            fluxSink.complete();
        }).subscribeOn(Schedulers.immediate());

        objectFlux
                .doFirst(() -> log.info("First1"))
                .subscribeOn(Schedulers.boundedElastic())
                .doFirst(() -> log.info("First2"))
                .subscribe(Util.subscriber());

        Util.sleepSeconds(2);
    }
}
