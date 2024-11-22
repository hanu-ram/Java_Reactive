package com.hanu.sec12;

import com.hanu.common.Util;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Sinks;

/**
 * Sinks.Many is a sink that can emit multiple values.
 * It is useful when you want to emit multiple values.
 * Sinks many unicast can have only one subscriber.
 * Until the complete signal is not emitted, sink can send values and subcriber can receive them throughout the process.
 */
@Slf4j
public class SinkManyUni {
    public static void main(String[] args) {
        demo1();
    }
    private static void demo1() {
        //for unicast the onBackpressureBuffer will give unbounded queue
        var sink = Sinks.many().unicast().onBackpressureBuffer();
        var flux = sink.asFlux();
        flux.subscribe(Util.subscriber("Sub 1"));
        sink.tryEmitNext("Hello");
        sink.tryEmitNext("World");
        sink.tryEmitNext("Reactive");
//        flux.subscribe(Util.subscriber("Sub 2")); // it will throw exception
//        sink.tryEmitComplete();
    }
}
