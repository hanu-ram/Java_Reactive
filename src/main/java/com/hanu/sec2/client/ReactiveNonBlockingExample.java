package com.hanu.sec2.client;

import com.hanu.common.Util;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
public class ReactiveNonBlockingExample {
    public static void main(String[] args) {
        var externalService = new ExternalServiceClient();
        for (int i = 1; i <= 100; i++) {
            Mono<String> productById = externalService.getProductById(i);
            productById.subscribe(Util.subscriber());
        }
        Util.sleepSeconds(2);
    }
}
