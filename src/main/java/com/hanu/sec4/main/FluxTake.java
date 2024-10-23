package com.hanu.sec4.main;

import reactor.core.publisher.Flux;

public class FluxTake {
    public static void main(String[] args) {
        Flux.range(1, 10)
                .take(3)
                .log()
                .subscribe(System.out::println);
       /* Flux.range(10, 20)
                .takeWhile(i -> i  < 15) //if condition is false then it will stop emitting
                .log()
                .subscribe(System.out::println);*/
        Flux.range(20, 30)
                .takeUntil(i -> i <= 25) //if condition is true then it will stop emitting
                .log()
                .subscribe(System.out::println);
    }
}
