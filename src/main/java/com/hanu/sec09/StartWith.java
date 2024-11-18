package com.hanu.sec09;

import com.hanu.common.Util;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
public class StartWith {
    public static void main(String[] args) {
        producer1()
//                .startWith(-3,-2,0) // used to combine two publishers and startsWith will be executed first which is near to sub
                .startWith(producer2())
                .subscribe(Util.subscriber());
    }

    public static Flux<Integer> producer1() {
        return Flux.just(1, 2, 3)
                .transform(Util.fluxLogger("Publisher1"));
    }

    public static Flux<Integer> producer2() {
        return Flux.just(51, 52, 53)
                .transform(Util.fluxLogger("Publisher2"));
    }
}
