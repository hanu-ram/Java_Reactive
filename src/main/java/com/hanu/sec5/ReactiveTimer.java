package com.hanu.sec5;

import com.hanu.common.Util;
import reactor.core.publisher.Mono;

import java.time.Duration;

/**
 * we can have multiple timeouts as well. The closest to the subscriber will take effect to the subscriber
 */
public class ReactiveTimer {
    public static void main(String[] args) {
        Mono<String> productName = getProductName()
                .timeout(Duration.ofSeconds(1), fallback());
        productName
//                .onErrorReturn("Laptop") // we can handle using onError methods as well
                .timeout(Duration.ofMillis(500)) // this is closest to the subscriber thus this will take the effect. and the timeout millis or secs should be less than the other higher timeout won't work because above timeout is throwing the exception
                .subscribe(Util.subscriber());
        Util.sleepSeconds(5);
    }

    private static Mono<String> getProductName() {
        return Mono.fromCallable(() -> Util.faker().commerce().productName())
                .delayElement(Duration.ofSeconds(3));
    }

    private static Mono<String> fallback() {
        return Mono.fromCallable(() -> Util.faker().color().name());
    }
}
