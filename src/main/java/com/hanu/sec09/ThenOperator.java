package com.hanu.sec09;

import com.hanu.common.Util;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;

@Slf4j
public class ThenOperator {
    public static void main(String[] args) {
        var records = List.of("1", "2", "3", "4", "5");
        saveRecords(records)
                .then(Mono.fromRunnable(() -> log.info("Successfully saved"))) // doesn't emit any data, it just waits for the completion of the previous operation.
                .subscribe(Util.subscriber());
        Util.sleepSeconds(5);
    }
    public static Flux<String> saveRecords(List<String> records) {
        return Flux.fromIterable(records)
                .map(r -> r + " saved")
                .delayElements(Duration.ofMillis(500));
    }
}
