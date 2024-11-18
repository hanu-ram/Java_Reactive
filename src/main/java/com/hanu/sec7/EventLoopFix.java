package com.hanu.sec7;

import com.hanu.common.Util;
import com.hanu.sec7.client.ExternalServiceClient;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

public class EventLoopFix {
    public static void main(String[] args) {
        var externalService = new ExternalServiceClient();
        for (int i = 1; i <= 5; i++) {
            Mono<String> productById = externalService.getProductById(i);
            productById
                    .map(EventLoopFix::process)
                    .subscribe(Util.subscriber());
        }
        Util.sleepSeconds(12);
    }
    private static String process(String product) {
        Util.sleepSeconds(1);
        return product + "-Processed";
    }
}
