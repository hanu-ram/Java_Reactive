package com.hanu.sec4.main;

import com.hanu.common.Util;
import reactor.core.publisher.Flux;

public class FluxCreate {
    public static void main(String[] args) {
        demo2();
    }

    private static void demo1() {
        Flux.create(fluxSink -> {
            fluxSink.next(1);
            fluxSink.next(1);
            fluxSink.next(1);
            fluxSink.next(1);
            fluxSink.complete();
        }).subscribe(Util.subscriber());
    }

    private static void demo2() {
        Flux.create(fluxSink -> {
            String colorName;
            do {
                colorName = Util.faker().color().name();
                fluxSink.next(colorName);
            } while (!colorName.equalsIgnoreCase("blue"));
        }).subscribe(Util.subscriber());
    }
}
