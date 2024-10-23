package com.hanu.sec5;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
public class FluxHandleOperator {
    public static void main(String[] args) {
        Flux.range(1, 10)
                .handle((integer, synchronousSink) -> {
                    if (integer != 5 && integer != 7) {
                        synchronousSink.next(integer);
                    }
                    if (integer == 7) {
                        synchronousSink.error(new RuntimeException("Error occurred"));
                    }
                }).subscribe(System.out::println);
    }
}
