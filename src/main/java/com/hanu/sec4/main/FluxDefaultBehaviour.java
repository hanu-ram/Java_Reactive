package com.hanu.sec4.main;

import com.hanu.common.Util;
import com.hanu.sec1.subscriber.SubscriberImpl;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
public class FluxDefaultBehaviour {
    /**
     * Flux sink is eager loading. Meaning if we have subscriber then it will execute the code and store the data in queue whenever subscriber request the data
     *  then it will emit data from queue. If we dont have subscriber it wont execute code. For complete lazy loading we can use fluxSink.onRequest()
     */
    public static void main(String[] args) {
        var subscriber = new SubscriberImpl();
        Flux<String> stringFlux = Flux.<String>create(fluxSink -> {
            for (int i = 0; i < 10; i++) {
                String name = Util.faker().color().name();
                log.info("generated name: {}", name);
                fluxSink.next(name);
            }
        });
        stringFlux.subscribe(subscriber);
        Util.sleepSeconds(2);
        subscriber.getSubscription().request(3);
        subscriber.getSubscription().request(2);
        subscriber.getSubscription().cancel();
        subscriber.getSubscription().request(2);
//        stringFlux.subscribe(Util.subscriber("Second Sub2"));
    }
}
