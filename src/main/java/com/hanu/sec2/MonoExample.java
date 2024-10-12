package com.hanu.sec2;

import com.hanu.common.SubscriberCommonImpl;
import com.hanu.sec1.subscriber.SubscriberImpl;
import reactor.core.publisher.Mono;

public class MonoExample {
    public static void main(String[] args) {
        var mono = Mono.just("123");
        SubscriberImpl subscriber = new SubscriberImpl();
        mono.subscribe(subscriber);
        subscriber.getSubscription().request(1);
//        subscriber.getSubscription().cancel();
        SubscriberCommonImpl<Integer> commonSub = new SubscriberCommonImpl<>("MonoJustConsumer");
        Mono.just(123 + 1).subscribe(commonSub);
    }
}
