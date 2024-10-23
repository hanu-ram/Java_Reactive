package com.hanu.sec4.main;

import com.hanu.common.Util;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
public class FluxGenerateAssignment {
    public static void main(String[] args) {
        Flux.<String>generate(synchronousSink -> synchronousSink.next(Util.faker().color().name()))
                .takeUntil(s -> s.equalsIgnoreCase("blue"))
                .subscribe(Util.subscriber());
    }
}
