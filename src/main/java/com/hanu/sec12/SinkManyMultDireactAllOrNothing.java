package com.hanu.sec12;

import com.hanu.common.Util;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Sinks;

import java.time.Duration;

@Slf4j
public class SinkManyMultDireactAllOrNothing {
    public static void main(String[] args) {
        demo1();
        Util.sleepSeconds(10);
    }
    private static void demo1() {
        System.setProperty("reactor.bufferSize.small", "16");
        //for multicast the onBackpressureBuffer will give bounded queue
        var sink = Sinks.many().multicast().directAllOrNothing(); // It will deliver if all subscribers are fast otherwise it won't deliver to any subscriber.
        var flux = sink.asFlux();
        flux.subscribe(Util.subscriber("sam"));
        flux.delayElements(Duration.ofMillis(200)).subscribe(Util.subscriber("mike"));

        for (int i = 1; i <= 1000; i++) {
            var emitResult = sink.tryEmitNext(i);
            log.info("Item: {} | Emit result: {}", i, emitResult);
        }
    }
}
