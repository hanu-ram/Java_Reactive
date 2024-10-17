package com.hanu.sec2;

import com.github.javafaker.Faker;
import com.hanu.common.SubscriberCommonImpl;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
public class MonoFromRunnable {
    private static final Faker faker = Faker.instance();

    public static void main(String[] args) {

        getProductById(1).subscribe(
                new SubscriberCommonImpl<>("MonoFromSupplier1")
        );
        getProductById(2).subscribe(
                new SubscriberCommonImpl<>("MonoFromSupplier2")
        );
    }

    private static Mono<String> getProductById(int id) {
        if (id == 1) {
            String productName = faker.commerce().productName();
            return Mono.just(productName);
//            return Mono.fromCallable(() -> faker.commerce().productName());
        }
//        return Mono.empty(); // In Some scenarios we may need to return empty Mono because user less user experience. User might want to know what is the product Id he searched. So, instead of returning empty we can atleast display the product id that is not available.
        return Mono.fromRunnable(() -> notifyUser(id));
    }

    private static void notifyUser(int id) {
        log.warn("User Notified with the product id {}", id);
    }
}
