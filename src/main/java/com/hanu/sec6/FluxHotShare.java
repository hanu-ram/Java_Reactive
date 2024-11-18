package com.hanu.sec6;

import com.hanu.common.Util;
import com.hanu.sec1.subscriber.SubscriberImpl;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.time.Duration;

@Slf4j
/**
 * Hot publisher is used to publish the messages and this publisher can be shared among multiple subscribers
 * Cold publisher is used to publish the messages and this publisher will publish the messages individually
 * To create hot publisher we can use share() method by default atleast one subscriber should be there in order to emit the messages
 * If messages started consumer 1 consumes and later any consumer subscribed then it wont get previous messages new consumer
 * will get the continuation
 */
public class FluxHotShare {
    public static void main(String[] args) {
        SubscriberImpl subscriber1 = new SubscriberImpl();
        SubscriberImpl subscriber2 = new SubscriberImpl();
        Flux<String> names = getNames().share();
        Util.sleepSeconds(2);
        names.subscribe(subscriber1);
        subscriber1.getSubscription().request(10);
        Util.sleepSeconds(3);
        names.subscribe(subscriber2);
        subscriber2.getSubscription().request(10);
        Util.sleepSeconds(1);
        subscriber1.getSubscription().cancel();
        Util.sleepSeconds(20);
    }

    private static Flux<String> getNames() {
        return Flux.generate(() -> {
                    log.info("Received the request");
                    return 1;
                }, (state, sink) -> {
                    var scene = "Movie scene " + state;
                    log.info("Playing scene + " + scene);
                    sink.next(scene);
                    return ++state;
                })
                .delayElements(Duration.ofSeconds(1))
                .take(20)
                .cast(String.class);
    }
}
