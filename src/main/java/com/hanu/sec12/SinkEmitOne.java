package com.hanu.sec12;

import com.hanu.common.Util;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;
/**
 * Sinks.One is a sink that can emit only one value, one error, or one completion signal.
 * It is useful when you want to emit only one value.
 * It is also useful when you want to emit a value from a sink and then complete the sink.
 * Sinks.One is a hot source or publisher.
 * It doesn't care about the number of subscribers.
 * usecase: If you want to emit the value whenever you want and you don't care about the number of subscribers.
 */
@Slf4j
public class SinkEmitOne {
    public static void main(String[] args) {
        demo2();
    }

    private static void demo1() {
        Sinks.One<Object> sink = Sinks.one();
        Mono<Object> mono = sink.asMono();
        mono.subscribe(Util.subscriber());
//        sink.tryEmitValue("Hello" );
//        sink.tryEmitEmpty();
//        sink.tryEmitError(new RuntimeException("Error occurred"));
    }
    private static void demo2() {
        var sink = Sinks.one();
        var mono = sink.asMono();
        mono.subscribe(Util.subscriber("Sub 2"));
        sink.tryEmitValue("Sink one demo 2");
        mono.subscribe(Util.subscriber("Sub 1"));
//        sink.tryEmitValue("Sink one demo 2"); // Sinks can emit even before subscription or after. It doesn't matter.
    }

    /**
     * Sink emitValue method can be used to emit a value and handle the signal type and emit result.
     * Basically, it will notify the subscriber about the signal type and emit result.
     */
    private static void demo3() {
        var sink = Sinks.one();
        var mono = sink.asMono();
        mono.subscribe(Util.subscriber("Sub 1"));
        sink.emitValue("Sink one demo3 - 1", (signalType, emitResult) -> {
            log.warn("demo3 - 1");
            log.info("Signal type: {}", signalType);
            log.info("Emit result: {}", emitResult);
            return false; // this flag will decide whether to retry or not.
        });
        // Since this is a mono it will emit only one value but here we are trying to emit another value. Thus it will throw an exception and handler will be called.
        sink.emitValue("Sink one demo3 - 2", (signalType, emitResult) -> {
            log.warn("demo3 - 2");
            log.info("Signal type: {}", signalType);
            log.info("Emit result: {}", emitResult);
            return false; // this flag will decide whether to retry or not.
        });
    }
}
