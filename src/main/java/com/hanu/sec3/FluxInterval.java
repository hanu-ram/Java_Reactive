package com.hanu.sec3;

import com.hanu.common.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class FluxInterval {
    public static void main(String[] args) {
        Flux.interval(Duration.ofMillis(500))
                .map(i -> Util.faker().animal().name())
                .subscribe(Util.subscriber());
        Util.sleepSeconds(5);
    }
}
