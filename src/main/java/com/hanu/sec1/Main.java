package com.hanu.sec1;

import com.hanu.common.Util;
import com.hanu.sec1.publisher.PublisherImpl;
import com.hanu.sec1.subscriber.SubscriberImpl;
import org.reactivestreams.Publisher;

public class Main {
    public static void main(String[] args) {
        demo3();
    }
    public static void demo1() {
        Publisher<String> publisher = new PublisherImpl();
        SubscriberImpl subscriber = new SubscriberImpl();
        publisher.subscribe(subscriber);
    }
    public static void demo2() {
        Publisher<String> publisher = new PublisherImpl();
        SubscriberImpl subscriber = new SubscriberImpl();
        publisher.subscribe(subscriber);
        subscriber.getSubscription().request(3);
        Util.sleepSeconds(2);
        subscriber.getSubscription().request(3);
        Util.sleepSeconds(2);
        subscriber.getSubscription().request(3);
        Util.sleepSeconds(2);
        subscriber.getSubscription().request(3);
    }
    public static void demo3() {
        Publisher<String> publisher = new PublisherImpl();
        SubscriberImpl subscriber = new SubscriberImpl();
        publisher.subscribe(subscriber);
        subscriber.getSubscription().request(3);
        Util.sleepSeconds(2);
        subscriber.getSubscription().cancel();
        Util.sleepSeconds(2);
        subscriber.getSubscription().request(3);
        Util.sleepSeconds(2);
        subscriber.getSubscription().request(3);
    }
}