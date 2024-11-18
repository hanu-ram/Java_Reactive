package com.hanu.sec7;

import com.hanu.common.Util;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

/**
 * One publisher can have multiple subscribeOn method but,
 * whatever subscribeOn method which is near to the publisher that will take the precedence.
 */
@Slf4j
public class MultipleSubscribeOn {
    public static void main(String[] args) {
        Flux<Object> objectFlux = Flux.create(fluxSink -> {
            for (int i = 1; i <= 3; i++) {
                log.info("{}", fluxSink.next(i));
            }
            fluxSink.complete();
        }).subscribeOn(Schedulers.parallel()); //newParallel() also we can use for creating the parallel thread with our own thread name

        objectFlux
                .doFirst(() -> log.info("First1"))
                .subscribeOn(Schedulers.boundedElastic())
                .doFirst(() -> log.info("First2"))
                .subscribe(Util.subscriber());

        Util.sleepSeconds(2);
    }
}
