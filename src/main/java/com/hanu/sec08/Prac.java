package com.hanu.sec08;

import com.hanu.common.Util;
import com.hanu.sec1.subscriber.SubscriberImpl;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

@Slf4j
public class Prac {
    public static void main(String[] args) {
        SubscriberImpl subscriber = new SubscriberImpl();
        Flux<String> flux = Flux.create(sink -> {
                    sink.onRequest((n) -> {
                        for (int i = 1; i <= n && !sink.isCancelled(); i++) {
                            log.info("Generating {}", i);
                            sink.next("ai" + i);
                            Util.sleepSeconds(Duration.ofMillis(50));
                        }
                        sink.complete();
                    });
                })
                .cast(String.class)
                .subscribeOn(Schedulers.newParallel("hanu"));

        flux
                .onBackpressureDrop() //it will control the flow based on the request and drop rest of the pushed items.
//                .onBackpressureLatest() //90% similar as drop(). But, it will hold the latest pushed item and drop rest and whenever subscriber processed and request for new then the hold item will be served.
                .log()
//                .limitRate(1)
                .publishOn(Schedulers.boundedElastic())
                .map(Prac::process)
                .subscribe(subscriber);
//        subscriber.getSubscription().cancel();
        subscriber.getSubscription().request(5);
    }

    private static String  process(String i) {
        log.info("Received {}", i);
        Util.sleepSeconds(1);
        return i;
    }
}
