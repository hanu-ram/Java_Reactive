package com.hanu;

import com.hanu.sec1.publisher.PublisherImpl;
import com.hanu.sec1.subscriber.SubscriberImpl;
import org.reactivestreams.Publisher;

import java.time.Duration;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        demo3();
    }
    public static void demo1() {
        Publisher<String> publisher = new PublisherImpl();
        SubscriberImpl subscriber = new SubscriberImpl();
        publisher.subscribe(subscriber);
    }
    public static void demo2() throws InterruptedException {
        Publisher<String> publisher = new PublisherImpl();
        SubscriberImpl subscriber = new SubscriberImpl();
        publisher.subscribe(subscriber);
        subscriber.getSubscription().request(3);
        Thread.sleep(Duration.ofSeconds(2));
        subscriber.getSubscription().request(3);
        Thread.sleep(Duration.ofSeconds(2));
        subscriber.getSubscription().request(3);
        Thread.sleep(Duration.ofSeconds(2));
        subscriber.getSubscription().request(3);
    }
    public static void demo3() throws InterruptedException {
        Publisher<String> publisher = new PublisherImpl();
        SubscriberImpl subscriber = new SubscriberImpl();
        publisher.subscribe(subscriber);
        subscriber.getSubscription().request(3);
        Thread.sleep(Duration.ofSeconds(2));
        subscriber.getSubscription().cancel();
        Thread.sleep(Duration.ofSeconds(2));
        subscriber.getSubscription().request(3);
        Thread.sleep(Duration.ofSeconds(2));
        subscriber.getSubscription().request(3);
    }
}