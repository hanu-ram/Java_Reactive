package com.hanu.sec3;

import com.hanu.common.SubscriberCommonImpl;
import com.hanu.common.Util;
import reactor.core.publisher.Flux;

public class FluxJust {
    public static void main(String[] args) {
        SubscriberCommonImpl<Integer> subscriber = Util.subscriber();
        Flux.just(1,2,4)
                .subscribe(subscriber);
    }
}
