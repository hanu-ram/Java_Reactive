package com.hanu.sec5.assignment.client;

import reactor.core.publisher.Mono;

public class ReactiveHttpClientImpl extends AbstractHttpClient {
    public Mono<String> getProductNameById(int id) {
        return client.get()
                .uri("/demo03/product/" + id)
                .responseContent()
                .asString()
                .next();
    }
    public Mono<String> fallbackForTimeout(int id) {
        return client.get()
                .uri("/demo03/timeout-fallback/product/" + id)
                .responseContent()
                .asString()
                .next();
    }
    public Mono<String> fallbackForEmpty(int id) {
        return client.get()
                .uri("/demo03/empty-fallback/product/" + id)
                .responseContent()
                .asString()
                .next();
    }
}
