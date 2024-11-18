package com.hanu.sec6;

import com.hanu.common.Util;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.time.Duration;

/**
 * to replay or cache the messages we can use replay() method
 * and it will work only with autoConnect() method
 */
@Slf4j
public class FluxReplay {
    public static void main(String[] args) {
        Flux<Integer> names = getStockPrices().replay(1).autoConnect(1);
        Util.sleepSeconds(2);
        names.subscribe(Util.subscriber("Sub1"));
        Util.sleepSeconds(3);
        names.subscribe(Util.subscriber("Sub2"));
        Util.sleepSeconds(1);
        Util.sleepSeconds(12);
    }

    private static Flux<Integer> getStockPrices() {
        return Flux.generate(sink -> sink.next(Util.faker().random().nextInt(23, 89)))
                .doOnNext(s -> log.info("Stock price is " + s))
                .delayElements(Duration.ofSeconds(1))
                .take(10)
                .cast(Integer.class);
    }
}
