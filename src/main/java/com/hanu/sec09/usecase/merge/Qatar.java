package com.hanu.sec09.usecase.merge;

import com.hanu.common.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Qatar {
    public static final String AIRLINE = "Qatar";

    public static Flux<Flight> getFlights() {
        return Flux.range(1, Util.faker().random().nextInt(4, 10))
                .delayElements(Duration.ofMillis(Util.faker().random().nextInt(400, 1200)))
                .map(i -> new Flight(AIRLINE, Util.faker().random().nextInt(400,1200)))
                .transform(Util.fluxLogger(AIRLINE));
    }
}
