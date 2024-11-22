package com.hanu.sec11;

import com.hanu.common.Util;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;
@Slf4j
public class RetryOperator {
    public static void main(String[] args) {
        demo6();
    }

    private static void demo6() {
        getCountryName()
                .retryWhen(Retry.fixedDelay(2, Duration.ofMillis(700))
                        .filter(e -> IllegalAccessException.class.equals(e.getClass()))) // can control to retry only on specific exception
                .subscribe(Util.subscriber());
        Util.sleepSeconds(5);
    }
    private static void demo5() {
        getCountryName()
                .retryWhen(Retry.fixedDelay(2, Duration.ofMillis(800))
                        .onRetryExhaustedThrow((r, signal ) -> signal.failure()) // instead of throwing retry exhausted can throw root exception
                )
                .subscribe(Util.subscriber());
        Util.sleepSeconds(5);
    }
    private static void demo4() {
        getCountryName()
                .retryWhen(Retry.fixedDelay(3, Duration.ofMillis(800))
                        .doBeforeRetry(e -> log.warn("retrying"))
                )
//                .retryWhen(Retry.max(3))
//                .retryWhen(Retry.indefinitely()) // same like retry()
                .subscribe(Util.subscriber());
        Util.sleepSeconds(5);
    }
    private static void demo3() {
        getCountryName()
                .retryWhen(Retry.fixedDelay(3, Duration.ofSeconds(1)))
                .subscribe(Util.subscriber());
        Util.sleepSeconds(5);
    }
    private static void demo2() {
        getCountryName()
                .retry(2)
                .subscribe(Util.subscriber());
    }

    private static void demo1() {
        getCountryName()
                .retry() // it will keep on retry util there is no error or exception
                .subscribe(Util.subscriber());
    }

    private static Mono<String> getCountryName() {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        return Mono.fromSupplier(() -> {
            if (atomicInteger.incrementAndGet() < 4) {
                throw new RuntimeException("Exception occurred");
            }
            return Util.faker().country().name();
        }).doOnError(e -> log.error(e.getMessage()));
    }

}
