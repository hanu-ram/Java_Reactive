package com.hanu.common;

import com.github.javafaker.Faker;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.Duration;

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
            Thread.sleep(Duration.ofSeconds(seconds));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
