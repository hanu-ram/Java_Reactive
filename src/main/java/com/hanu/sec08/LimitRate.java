package com.hanu.sec08;

import com.hanu.common.Util;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;
@Slf4j
public class LimitRate {
    public static void main(String[] args) {
        System.setProperty("reactor.bufferSize.small", "16"); //as of now min upto 16 we can set by default 256
        Flux<Integer> flux = Flux.generate(() -> 0, (i, sink) -> {
            log.info("Generating - {}", i);
            sink.next(i);
            return ++i;
        }).cast(Integer.class).subscribeOn(Schedulers.newParallel("hanu"));

        flux.limitRate(5) // by using this we can limit the flow in the queue
                .publishOn(Schedulers.boundedElastic())
                .map(LimitRate::process)
                .subscribe(Util.subscriber());
    }

    private static int process(int i) {
        Util.sleepSeconds(1);
        return i;
    }
}
