package com.hanu.sec6;

import com.hanu.common.Util;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.time.Duration;

/**
 * share() and publish.refCount() both are for same use. Actually equivalent
 * But if you use refCount() then we can set the minimum subscribers count
 * so that if it matches the count then publisher will start publishing
 */
@Slf4j
public class FluxHotRef {
    public static void main(String[] args) {
        Flux<String> names = getNames().publish().refCount(2);
        Util.sleepSeconds(2);
        names.take(10).subscribe(Util.subscriber("Sub1"));
        Util.sleepSeconds(3);
        names.take(2).subscribe(Util.subscriber("Sub2"));
        Util.sleepSeconds(1);
        Util.sleepSeconds(12);
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
                .take(10)
                .cast(String.class);
    }
}
