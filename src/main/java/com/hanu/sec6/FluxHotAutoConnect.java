package com.hanu.sec6;

import com.hanu.common.Util;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.time.Duration;

/**
 * Same like share() and refCount(), autoConnect() also used for same purpose
 * by default the min subscribers count is 1 if 1 subscriber is there then publisher will start publishing
 * but with autoConnect() we can create real hot subscriber because even without single subscriber,
 * publisher can start publishing
 */
@Slf4j
public class FluxHotAutoConnect {
    public static void main(String[] args) {
        Flux<String> names = getNames().publish().autoConnect(0);
        Util.sleepSeconds(2);
        names.subscribe(Util.subscriber("Sub1"));
        Util.sleepSeconds(3);
        names.subscribe(Util.subscriber("Sub2"));
        Util.sleepSeconds(1);
        Util.sleepSeconds(10);
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
