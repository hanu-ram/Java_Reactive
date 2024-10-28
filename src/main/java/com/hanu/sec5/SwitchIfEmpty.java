package com.hanu.sec5;

import com.hanu.common.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class SwitchIfEmpty {
    public static void main(String[] args) {
        Mono.empty()
                .switchIfEmpty(fallback())
                .subscribe(Util.subscriber("mono"));

        Flux.range(1, 10)
                .filter(i -> i > 10)
                .switchIfEmpty(fallback2())
                .subscribe(Util.subscriber("flux"));
    }

    public static Mono<String> fallback() {
        return Mono.fromSupplier(() -> Util.faker().animal().name());
    }
    public static Flux<Integer> fallback2() {
        return Flux.create(integerFluxSink -> {
            integerFluxSink.next(Util.faker().random().nextInt(12, 20));
            integerFluxSink.complete();
        });
    }
}
