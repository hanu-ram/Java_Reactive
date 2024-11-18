package com.hanu.common;

import com.github.javafaker.Faker;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.function.UnaryOperator;
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Util {
    public static Faker faker() {
        return Faker.instance();
    }
    public static <T> SubscriberCommonImpl<T> subscriber() {
        return new SubscriberCommonImpl<>("");
    }

    public static <T> SubscriberCommonImpl<T> subscriber(String name) {
        return new SubscriberCommonImpl<>(name);
    }

    public static void sleepSeconds(int seconds) {
        try {
            Thread.sleep(Duration.ofSeconds(seconds).toMillis());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void sleepSeconds(Duration duration) {
        try {
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    public static <T> UnaryOperator<Flux<T>> fluxLogger(String name) {
        return flux -> flux.doOnSubscribe((n) -> log.info("Subscribed to {}", name))
                .doOnCancel(() -> log.info("Cancelled the {}", name))
                .doOnComplete(() -> log.info("Completed the {}", name));
    }
}
