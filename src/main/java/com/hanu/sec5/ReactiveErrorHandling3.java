package com.hanu.sec5;

import com.hanu.common.Util;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
public class ReactiveErrorHandling3 {
    public static void main(String[] args) {
//        Mono.error(new RuntimeException())
        Mono.just(4)
                .onErrorComplete()
                .subscribe(Util.subscriber());
    }
}
