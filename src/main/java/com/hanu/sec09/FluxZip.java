package com.hanu.sec09;

import com.hanu.common.Util;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.time.Duration;

@Slf4j
public class FluxZip {
    record Car(String body, String engine, String tires){};
    public static void main(String[] args) {
        Flux.zip(getBody(), getEngine(), getTires())
                .map(tuple -> new Car(tuple.getT1(), tuple.getT2(), tuple.getT3()))
                .subscribe(Util.subscriber());
        Util.sleepSeconds(2);
    }
    public static Flux<String> getBody() {
        return Flux.range(1, 3)
                .map(i -> "body-" + i)
                .transform(Util.fluxLogger("body"))
                .delayElements(Duration.ofMillis(50));
    }

    public static Flux<String> getEngine() {
        return Flux.range(1, 5)
                .map(i -> "engine-" + i)
                .transform(Util.fluxLogger("engine"));
//                .delayElements(Duration.ofMillis(50));
    }
    public static Flux<String> getTires() {
        return Flux.range(1, 12)
                .map(i -> "tires-" + i)
                .transform(Util.fluxLogger("tires"))
                .delayElements(Duration.ofMillis(50));
    }
}
