package com.hanu.sec09;

import com.hanu.common.Util;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
public class ConcatWith {
    public static void main(String[] args) {
        demo2();
    }

    /**
     * If we have Multiple exceptions then concatDelayError throw Multiple exceptions error
     * Thus, It can bear only one exception.
     */
    public static void demo5() {
        Flux.concatDelayError(producer1(), producer4(), producer3(), producer4())
                .subscribe(Util.subscriber()); // we also have Flux.concatWith()
    }

    /**
     * flux factory method concatDelayError will hold the exception that caused by any producer
     * And execute rest and then throw exception
     */
    public static void demo4() {
        Flux.concatDelayError(producer1(), producer3(), producer2())
                .subscribe(Util.subscriber()); // we also have Flux.concatWith()
    }

    public static void demo3() {
        producer1() //this will publish events.
                .concatWith(producer3()) //produces error
                .concatWith(producer2())
                .subscribe(Util.subscriber());
    }

    /**
     * concatWith(subscriber)
     */
    public static void demo2() {
        producer1()
                .concatWith(producer2()) // first producer 1 will execute and then if subscriber requested more events, then producer2 will execute same like startsWith but only
                .subscribe(Util.subscriber()); //difference is startWith will execute its producer first (close to sub) but concatWith execute in order.
    }

    /**
     * Normal concatWithValues
     */
    public static void demo1() {
        producer1()
                .concatWithValues(-3, -2, -1, 0)
                .subscribe(Util.subscriber());
    }

    public static Flux<Integer> producer1() {
        return Flux.just(1, 2, 3)
                .transform(Util.fluxLogger("producer1"));
    }

    public static Flux<Integer> producer2() {
        return Flux.just(51, 52, 53)
                .transform(Util.fluxLogger("producer2"));
    }

    public static Flux<Integer> producer3() {
        return Flux.error(new RuntimeException("Flux error producer3"));
    }
    public static Flux<Integer> producer4() {
        return Flux.error(new RuntimeException("Flux error producer4"));
    }

}
