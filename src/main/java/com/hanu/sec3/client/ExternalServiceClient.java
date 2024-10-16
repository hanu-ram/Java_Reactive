package com.hanu.sec3.client;

import reactor.core.publisher.Mono;

public class ExternalServiceClient extends AbstractHttpClient {

    public Mono<String> getProductById(int productId) {
        return client.get()
                .uri("/demo01/product/" + productId)
                .responseContent()
                .asString()
                .next();
    }
}
