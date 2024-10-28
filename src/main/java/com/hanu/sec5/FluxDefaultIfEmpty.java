package com.hanu.sec5;

import com.hanu.common.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class FluxDefaultIfEmpty {
    public static void main(String[] args) {
        Mono.just("Hello")
                .flatMap(s -> Mono.empty())
                .defaultIfEmpty("Hii")
                .subscribe(Util.subscriber("Mono"));

        Flux.range(1, 10)
                .filter(i -> i > 11)
                .defaultIfEmpty(Util.faker().random().nextInt(20, 50))
                .subscribe(Util.subscriber("Flux"));
    }
}
