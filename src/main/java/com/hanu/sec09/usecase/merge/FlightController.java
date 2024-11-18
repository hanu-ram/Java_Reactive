package com.hanu.sec09.usecase.merge;

import com.hanu.common.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class FlightController {
    public static Flux<Flight> getFlights() {
        return Flux.merge(Emirates.getFlights(), AirIndia.getFlights(), Qatar.getFlights())
                .take(Duration.ofSeconds(5));
    }

    public static void main(String[] args) {
        FlightController.getFlights().subscribe(Util.subscriber());
        Util.sleepSeconds(6);
    }
}
