package com.hanu.sec5;

import com.hanu.common.Util;
import com.hanu.sec5.helper.Employee;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Slf4j
public class DoOnNext {
    public static void main(String[] args) {
        Flux.range(1, 10)
                .doOnNext(i -> log.info("doOnNext() {}", i)) // we can use this to set objects also
//                .delaySequence(Duration.ofSeconds(1))
                .delayElements(Duration.ofSeconds(1))
                .subscribe(Util.subscriber());

        Util.sleepSeconds(12);
        Mono.fromCallable(() -> new Employee())
                .doOnNext(e -> e.setName("Hanumanth"))
                .subscribe(Util.subscriber("Employee"));
    }

}
