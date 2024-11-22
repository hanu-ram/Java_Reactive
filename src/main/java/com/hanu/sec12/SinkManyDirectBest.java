package com.hanu.sec12;

import com.hanu.common.Util;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Sinks;
import reactor.util.concurrent.Queues;

import java.time.Duration;

@Slf4j
public class SinkManyDirectBest {
    public static void main(String[] args) {
        demo2();
        Util.sleepSeconds(10);
    }

    /** problem that we will get when any subscriber is slow
     *  Slow subscriber will effect the fast subscriber performance
     */

    private static void demo1() {
        System.setProperty("reactor.bufferSize.small", "16");
        //for multicast the onBackpressureBuffer will give bounded queue
        var sink = Sinks.many().multicast().onBackpressureBuffer(100); // even if we increase the buffer size it wont help. even it helps to deliver messages but it will effect the performance of the fast subscriber.
        var flux = sink.asFlux();
        flux.subscribe(Util.subscriber("sam"));
        flux.delayElements(Duration.ofMillis(200)).subscribe(Util.subscriber("mike"));

        for (int i = 1; i <= 100; i++) {
            var emitResult = sink.tryEmitNext(i);
            log.info("Item: {} | Emit result: {}", i, emitResult);
        }
    }
    private static void demo2() {
        System.setProperty("reactor.bufferSize.small", "16");
        //for multicast the onBackpressureBuffer will give bounded queue
        var sink = Sinks.many().multicast().directBestEffort(); //it focus on the best performant subscriber and deliver the messages to that subscriber fast, and slow subscribers may loose.
        var flux = sink.asFlux();
        flux.subscribe(Util.subscriber("sam"));
        flux.onBackpressureBuffer().delayElements(Duration.ofMillis(200)).subscribe(Util.subscriber("mike")); // to avoid that to slow subscribers we have to handle the backpressure strategy. Just it says i am slow subscriber put all the messages in buffer queue i will take and process it.

        for (int i = 1; i <= 1000; i++) {
            var emitResult = sink.tryEmitNext(i);
            log.info("Item: {} | Emit result: {}", i, emitResult);
        }
    }
}
