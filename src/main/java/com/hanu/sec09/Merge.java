package com.hanu.sec09;

import com.hanu.common.Util;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.time.Duration;

@Slf4j
public class Merge {
    public static void main(String[] args) {
        demo2();
        Util.sleepSeconds(2);
    }

    /**
     * Merge method will merge all the publishers and publish the events
     * There is no specific order it depends on how fast the publisher emits.
     * if we use Flux.merge(), all producers will be subscribed at a same time
     */
    public static void demo2() {
        Flux.merge(producer1(), producer2(), producer3())
                .subscribe(Util.subscriber());
    }

    public static void demo1() {
        producer1()
                .mergeWith(producer2())
                .subscribe(Util.subscriber());
    }

    public static Flux<Integer> producer1() {
        return Flux.just(1, 2, 3)
                .transform(Util.fluxLogger("producer1"))
                .delayElements(Duration.ofMillis(50));
    }

    public static Flux<Integer> producer2() {
        return Flux.just(51, 52, 53)
                .transform(Util.fluxLogger("producer2"));
//                .delayElements(Duration.ofMillis(50));
    }
    public static Flux<Integer> producer3() {
        return Flux.just(11, 12, 13)
                .transform(Util.fluxLogger("producer3"))
                .delayElements(Duration.ofMillis(50));
    }
}
