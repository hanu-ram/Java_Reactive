package com.hanu.sec7;

import com.hanu.common.Util;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

@Slf4j
public class SubscribeOn {
    /**
     * Subscribe on is for telling the reactive to execute the code in new thread
     * And we have to specify what is the Scheduler we want to use
     * we have parallel, immediate, boundedElastic and single
     * parallel is for compute intensive tasks
     * boundedElastic is for network and IO related tasks
     * If we want to run the boundedElastic in virtual thread
     * we have to enable one property and virtual thread is also used for network related
     */
    public static void main(String[] args) {
        Flux<Object> objectFlux = Flux.create(fluxSink -> {
            for (int i = 1; i <= 3; i++) {
                log.info("{}", fluxSink.next(i));
            }
            fluxSink.complete();
        });

        objectFlux
                .doFirst(() -> log.info("First1"))
                .subscribeOn(Schedulers.boundedElastic())
                .doFirst(() -> log.info("First2"))
                .subscribe(Util.subscriber());

        Util.sleepSeconds(2);
    }
}
