package com.hanu.sec7;

import com.hanu.common.Util;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;
@Slf4j
/**
 *  here publishOn and subscribeOn mainly we have to use it for non blocking io calls.
 *  here first publish at map process will execute and send the result to publish On and this one will send it to other operator
 *  which is map() and will go back to previous map to process because at second map we have another publish on which will
 *  handle from that point
 */
public class NonBlockingRealExample {
    public static void main(String[] args) {
        long s = System.currentTimeMillis();
        Flux.range(1, 10)
                .map(NonBlockingRealExample::process)
//                .publishOn(Schedulers.boundedElastic()) //20278 time taken without this
                .map(NonBlockingRealExample::process)
                .publishOn(Schedulers.parallel())
                .subscribe(Util.subscriber());
        log.info("Time taken {}", System.currentTimeMillis() - s); //10211
    }
    public static int process(int i) {
        Util.sleepSeconds(1);
        return i * 10;
    }
}
