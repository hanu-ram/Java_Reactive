package com.hanu.sec2;

import com.hanu.common.Util;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;
@Slf4j
public class MonoFromFuture {
    public static void main(String[] args) {
       // Mono.fromFuture(getName());              // with future it will execute method eagerly meaning even without subscriber it will execute.
//            .subscribe(Util.subscriber());

        Mono.fromCallable(MonoFromFuture::getName)
                .subscribe(Util.subscriber());// with callable/Supplier it will execute method lazily meaning it will execute only when there is a subscriber.
        Util.sleepSeconds(1);
    }
    private static CompletableFuture<String> getName() {
        log.info("Executing the future to get fullName");
        return CompletableFuture.supplyAsync(() -> Util.faker().name().fullName());
    }
}
