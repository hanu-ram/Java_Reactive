package com.hanu.sec4.main;

import com.hanu.common.Util;
import reactor.core.publisher.Flux;

public class FluxGenerateWithTake {
    public static void main(String[] args) {
        Flux.generate(synchronousSink -> {
                    synchronousSink.next("Hello");
                //    synchronousSink.complete(); // if we have complete method it wont emit the data.
                }).log().take(5).
                subscribe(Util.subscriber());
    }
}
