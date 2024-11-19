package com.hanu.sec09;

import com.hanu.common.Util;
import reactor.core.publisher.Flux;

public class CollectList {
    public static void main(String[] args) {
        Flux.range(1, 10)
//                .concatWith(Flux.error(new RuntimeException("error")))
                .collectList() // it will collect all the data and return as a Mono<List>
                .subscribe(Util.subscriber());
    }
}
