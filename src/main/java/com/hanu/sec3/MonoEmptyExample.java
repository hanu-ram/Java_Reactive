package com.hanu.sec3;

import com.hanu.common.SubscriberCommonImpl;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
public class MonoEmptyExample {
    public static void main(String[] args) {
        Mono<Object> empty = Mono.empty();
        empty.subscribe(new SubscriberCommonImpl<>("Mono-Empty"));

        Mono<Object> error = Mono.error(new IllegalArgumentException("Error"));
        error.subscribe(new SubscriberCommonImpl<>("Error"));
    }
}
