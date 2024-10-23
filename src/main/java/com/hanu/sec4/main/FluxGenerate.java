package com.hanu.sec4.main;

import com.hanu.sec1.subscriber.SubscriberImpl;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
public class FluxGenerate {
    /**
     * Flux sink is eager loading. Meaning if we have subscriber then it will execute the code and store the data in queue whenever subscriber request the data
     *  then it will emit data from queue. If we don't have subscriber it won't execute code. For complete lazy loading we can use fluxSink.onRequest()
     *  But Flux generate is lazy loading. It will execute the code only when subscriber request the data. even though there is a subscriber it wont execute the code
     */
    public static void main(String[] args) {

        SubscriberImpl subscriber = new SubscriberImpl();
        Flux.<String >generate(synchronousSink ->  {
            log.info("Emitting Hello");
            synchronousSink.next("Hello");
            synchronousSink.complete();
        }).subscribe(subscriber);
        subscriber.getSubscription().request(1);
    }
}
