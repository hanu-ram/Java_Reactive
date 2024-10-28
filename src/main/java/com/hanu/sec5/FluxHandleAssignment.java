package com.hanu.sec5;

import com.hanu.common.Util;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
public class FluxHandleAssignment {
    public static void main(String[] args) {
        Flux.generate(synchronousSink -> synchronousSink.next(Util.faker().color().name()))
                .cast(String.class)
                .handle((color, sink) -> {
                    sink.next(color);
                    if (color.equalsIgnoreCase("black")) {
                        sink.complete();
                    }

                }).subscribe(Util.subscriber());
    }
}
