package com.hanu.sec11;

import com.hanu.common.Util;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;

public class Repeat {
    public static void main(String[] args) {
        demo4();
        Util.sleepSeconds(4);
    }

    private static void demo4() {
        getCountry()
                .repeatWhen(flux -> flux.delayElements(Duration.ofSeconds(1)).take(3)) // repeat 3 times
                .subscribe(Util.subscriber());
    }

    private static void demo3() {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        getCountry()
                .repeat(() -> atomicInteger.incrementAndGet() < 3) // repeat 3 times
                .subscribe(Util.subscriber());
    }

    private static void demo2() {
        getCountry()
                .repeat() // infinite repeat
                .takeUntil(country -> country.equalsIgnoreCase("canada"))
                .subscribe(Util.subscriber());
    }

    private static void demo1() {
        getCountry()
                .repeat(2) // it will repeat the flux 2 times only if it gets complete signal.
                .subscribe(Util.subscriber());
    }

    private static Mono<String> getCountry() {
        return Mono.fromSupplier(() -> Util.faker().country().name());
    }
}
