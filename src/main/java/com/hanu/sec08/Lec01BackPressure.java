package com.hanu.sec08;

import com.hanu.common.Util;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;
import reactor.util.concurrent.Queues;

@Slf4j
/**
 * In Flux.generate() backpressure is automatically handled
 */
public class Lec01BackPressure {
    public static void main(String[] args) {
        System.setProperty("reactor.bufferSize.small", "16"); //as of now min upto 16 we can set by default 256
        Flux<Integer> flux = Flux.generate(() -> 0, (i, sink) -> {
            log.info("Generating - {}", i);
            sink.next(i);
            return ++i;
        }).cast(Integer.class).subscribeOn(Schedulers.newParallel("hanu"));

        flux.publishOn(Schedulers.boundedElastic())
                .map(Lec01BackPressure::process)
                .subscribe(Util.subscriber());
    }

    private static int process(int i) {
        Util.sleepSeconds(1);
        return i;
    }
}
