package com.hanu.sec2;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
public class MonoJustConsumer {
    public static void main(String[] args) {
        var mono = Mono.just(10 + 20);
        mono.subscribe(
                item -> log.info("Consumed item is: {}", item),
                error -> log.error("Error occurred: ", error)
        );
        Mono<Integer> map = mono.map(i -> i / 0);
        map.subscribe(
                item1 -> log.info("Consumed item1 is: {}", item1),
                error -> log.error("Error occurred: ", error),
                () -> log.info("Completed Successfully")
        );
    }
}
