package com.hanu.sec5;

import com.hanu.common.Util;
import reactor.core.publisher.Mono;

public class ReactiveErrorHandling2 {
    public static void main(String[] args) {
        Mono.just(5)
//        Mono.error(new RuntimeException())
                .map(integer -> integer == 5 ? integer / 0 : integer)
//                .onErrorResume(e -> fallback()) // give another fallback publisher
                .onErrorResume(ArithmeticException.class, e -> fallback())
                .onErrorReturn(0)
                .subscribe(Util.subscriber());
    }

    private static Mono<Integer> fallback() {
        return Mono.fromSupplier(() -> Util.faker().random().nextInt(10, 50));
    }
}
