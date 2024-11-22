package com.hanu.sec12;

import com.hanu.common.Util;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Sinks;

@Slf4j
public class SinkManyMulti {
    public static void main(String[] args) {
        demo2();
    }

    private static void demo1() {
        //for multicast the onBackpressureBuffer will give bounded queue
        var sink = Sinks.many().multicast().onBackpressureBuffer();
        var flux = sink.asFlux();
        flux.subscribe(Util.subscriber("sam"));
        sink.tryEmitNext(1);
        sink.tryEmitNext(2);
        sink.tryEmitNext(3);
        sink.tryEmitNext(4);
        flux.subscribe(Util.subscriber("mike"));
        sink.tryEmitNext(5);
        sink.tryEmitNext(6);
        sink.tryEmitNext(7);
        sink.tryEmitNext(8);
        sink.tryEmitNext(9);
        Util.sleepSeconds(2);
        sink.tryEmitNext(10);
        sink.tryEmitNext(11);
    }

    /**
     * Sink many multicast have the capability called warm up.
     * if we are emitting values even if we dont have any subscriber. Then the values are going to store in the bounded queue. with limited size or we can configure the size in the parameter
     * onBackpressureBuffer(5) Example
     * When it finds any subscriber it will deliver all the messages that are stored in the queue.
     * late subscribers wont get those messages that are emitted before they subscribed.
     * They will get only new messages.
     */
    private static void demo2() {
        //for multicast the onBackpressureBuffer will give bounded queue
        var sink = Sinks.many().multicast().onBackpressureBuffer();
        var flux = sink.asFlux();
        sink.tryEmitNext(1);
        sink.tryEmitNext(2);
        sink.tryEmitNext(3);
        sink.tryEmitNext(4);

        flux.subscribe(Util.subscriber("sam"));
        flux.subscribe(Util.subscriber("mike"));
        Util.sleepSeconds(2);
        sink.tryEmitNext(5);
        sink.tryEmitNext(6);

    }
}
