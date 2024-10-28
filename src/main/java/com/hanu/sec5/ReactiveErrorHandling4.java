package com.hanu.sec5;

import com.hanu.common.Util;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
@Slf4j
public class ReactiveErrorHandling4 {
    public static void main(String[] args) {
        Flux.range(1, 10)
                .map(integer -> integer == 5 ? integer / 0 : integer)
                .onErrorContinue((e, o) -> log.error("Failed {}", o))
                .subscribe(Util.subscriber());
    }
}
