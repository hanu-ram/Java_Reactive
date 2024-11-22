package com.hanu.sec12;

import com.hanu.common.Util;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Sinks;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

@Slf4j
public class SinkManyThreadSafe {
    public static void main(String[] args) {
        demo2();
    }

    /**
     * In this example we can see why its not thread safe.
     */
    private static void demo1() {
        var sink = Sinks.many().unicast().onBackpressureBuffer();
        var flux = sink.asFlux();
        var list = new ArrayList<>();
        flux.subscribe(list::add);
        for (int i = 0; i < 1000; i++) {
            var j = i;
            CompletableFuture.runAsync(() -> sink.tryEmitNext(j));
        }
        Util.sleepSeconds(1);
        log.info("demo1 List size: {}", list.size());
    }

    private static void demo2() {
        var sink = Sinks.many().unicast().onBackpressureBuffer();
        var flux = sink.asFlux();
        var list = new ArrayList<>();
        flux.subscribe(list::add);
        for (int i = 0; i < 1000; i++) {
            var j = i;
            CompletableFuture.runAsync(() -> {
                Util.sleepSeconds(1);
                sink.emitNext(j, (signalType, emitResult) -> {
                    log.info("Signal type: {}", signalType);
                    return Sinks.EmitResult.FAIL_NON_SERIALIZED.equals(emitResult);
                });

            });
        }
        Util.sleepSeconds(70);
        log.info("demo2 List size: {}", list.size());
    }
}
