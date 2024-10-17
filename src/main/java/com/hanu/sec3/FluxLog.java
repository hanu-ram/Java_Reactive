package com.hanu.sec3;

import com.hanu.common.Util;
import reactor.core.publisher.Flux;

public class FluxLog {
    public static void main(String[] args) {
        /**
         * Here log() is a operator meaning implements both Publisher and Subscriber
         * As log() is a processor or operator it can be both publisher and subscriber
         * Here in this case after range() log() is consuming so its acting as a consumer or subscriber
         * and sending or publishing messages to subscribe() method here it is acting as a publisher
         */
        Flux.range(1,10)
                .log()
                .map(i -> i * 2)
                .subscribe(Util.subscriber());

        Flux.range(1,10)
                .filter(i-> i % 2 == 0)
                .log()
                .map(i -> i * 2)
                .log()
                .subscribe(Util.subscriber());
    }
}
