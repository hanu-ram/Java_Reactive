package com.hanu.sec7.client;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Slf4j
public class ExternalServiceClient extends AbstractHttpClient {
    public Mono<String> getProductById(int productId) {
        return client.get()
                .uri("/demo01/product/" + productId)
                .responseContent()
                .asString()
                .doOnNext(v -> log.info("next: {}", v))
                .next()
                .publishOn(Schedulers.boundedElastic());
    }
}
