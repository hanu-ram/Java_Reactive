package com.hanu.sec09.services;

import reactor.core.publisher.Mono;

import java.util.Map;

public class PaymentService {
    private static final Map<Integer, Integer> paymentDB = Map.of(
            1, 100,
            2, 200,
            3, 300
    );

    public static Mono<Integer> getUserBalance(Integer id) {
        return Mono.fromSupplier(() -> paymentDB.get(id));
    }

}
