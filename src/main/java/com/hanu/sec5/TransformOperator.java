package com.hanu.sec5;

import com.hanu.common.Util;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.util.function.UnaryOperator;

@Slf4j
public class TransformOperator {
    record Customer(int id, String name) {
    }

    record Product(String productName, int price, int quantity) {
    }

    public static void main(String[] args) {
        getAllCustomers()
                .transform(addDebugger()) // we can use this transform especially for reusing common operations and also if you want to do some transformation. It will return a new Publisher.
                .subscribe();
        getAllProducts()
                .subscribe();
    }

    private static Flux<Customer> getAllCustomers() {
        return Flux.range(1, 3)
                .map(i -> new Customer(i, Util.faker().name().firstName()));
    }

    private static Flux<Product> getAllProducts() {
        return Flux.range(1, 5)
                .map(i -> new Product(Util.faker().commerce().productName(), i * 100, i * 5));
    }

    /**
     * Common code for two publishers.
     * @return UnaryOperator
     */
    private static <T> UnaryOperator<Flux<T>> addDebugger() {
        return flux -> flux
                .doOnNext(request -> log.info("Request {}", request))
                .doOnError(error -> log.error(error.getMessage()))
                .doOnComplete(() -> log.info("Completed Successfully"));
    }
}
