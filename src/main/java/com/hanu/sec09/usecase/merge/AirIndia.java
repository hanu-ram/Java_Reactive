package com.hanu.sec09.usecase.merge;

import com.hanu.common.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class AirIndia {
    public static final String AIRLINE = "AirIndia";

    public static Flux<Flight> getFlights() {
        return Flux.range(1, Util.faker().random().nextInt(3, 10))
                .delayElements(Duration.ofMillis(Util.faker().random().nextInt(100, 800)))
                .map(i -> new Flight(AIRLINE, Util.faker().random().nextInt(200,1000)))
                .transform(Util.fluxLogger(AIRLINE));
    }
}
