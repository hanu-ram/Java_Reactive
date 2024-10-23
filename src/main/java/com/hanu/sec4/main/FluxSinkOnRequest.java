package com.hanu.sec4.main;

import com.hanu.common.Util;
import com.hanu.sec1.subscriber.SubscriberImpl;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
public class FluxSinkOnRequest {
    public static void main(String[] args) {
        SubscriberImpl subscriber = new SubscriberImpl();
       Flux.<String>create(fluxSink -> {
            fluxSink.onRequest(n -> {
                int max = 10;
                for (int i = 0; i < n; i++) {
                    if (max == 0) {
                        log.info("Reached the limit of 10");
                        fluxSink.complete();
                        break;
                    }
                    max--;
                    String name = Util.faker().color().name();
                    log.info("Generated name: {}", name);
                    fluxSink.next(name);
                }
            });
        })
                .subscribe(subscriber);
        subscriber.getSubscription().request(2);
        subscriber.getSubscription().cancel();
        subscriber.getSubscription().request(5);
    }
}
