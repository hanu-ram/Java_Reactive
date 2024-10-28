package com.hanu.sec5;

import com.hanu.common.Util;
import reactor.core.publisher.Flux;

public class ReactiveErrorHandling1 {
    public static void main(String[] args) {
        Flux.range(1, 10)
                .map(integer -> integer == 5 ? integer / 0 : integer)
//                .onErrorReturn(0)
                .onErrorReturn(ArithmeticException.class, 0) //only if arithmetic exception then this will execute. second onErrorReturn wont execute
                .onErrorReturn(-1) //any exception this will execute
                .subscribe(Util.subscriber());
    }
}
