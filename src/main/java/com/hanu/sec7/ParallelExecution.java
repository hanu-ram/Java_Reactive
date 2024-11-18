package com.hanu.sec7;

import com.hanu.common.Util;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class ParallelExecution {
    public static void main(String[] args) {
        Flux.range(1,10)
                .parallel()
                .runOn(Schedulers.newParallel("hanu"))
                .map(NonBlockingRealExample::process)
                .sequential()
                .map(i -> i + "-processed")
                .subscribe(Util.subscriber());
    }
}
