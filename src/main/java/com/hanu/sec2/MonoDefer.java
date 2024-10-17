package com.hanu.sec2;

import com.hanu.common.Util;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
public class MonoDefer {
    public static void main(String[] args) {
        Mono.defer(MonoDefer::createPublisher) // if we use Mono.defer(), it will get into the createPublisher method if there is subscriber so we can achive lazy loading.
                .subscribe(Util.subscriber());
    }
    private static Mono<Integer> createPublisher() {
        log.info("Inside createPublisher method");
        Util.sleepSeconds(1);
        List<Integer> integers = List.of(1, 2, 3, 4, 5);
        return Mono.fromCallable(() -> getSum(integers));
    }
    private static int getSum(List<Integer> list) {
        log.info("Inside getSum method");
        Util.sleepSeconds(3);
        return list.stream().mapToInt(Integer::intValue).sum();
    }
}
