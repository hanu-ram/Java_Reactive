package com.hanu.sec4.main;

import com.hanu.common.Util;
import reactor.core.publisher.Flux;

public class FluxGenerateStateSupplier {
    public static void main(String[] args) {
        Flux.generate(() -> 0, (count, sink) -> {
            String color = Util.faker().color().name();
            sink.next(color);
            if (count == 10 || color.equalsIgnoreCase("blue")) {
                sink.complete();
            }
            count = count + 1;
            return count;
        }).subscribe(Util.subscriber());
    }
}
