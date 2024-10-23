package com.hanu.sec4.assignment;

import com.hanu.sec1.subscriber.SubscriberImpl;
import lombok.extern.slf4j.Slf4j;
@Slf4j
public class Main {
    public static void main(String[] args) {
        FileReaderService fileReaderService = new FileReaderServiceImpl();
        SubscriberImpl subscriber = new SubscriberImpl();
        fileReaderService.read("src/main/java/com/hanu/sec4/assignment/test.txt")
                .subscribe(subscriber);
        subscriber.getSubscription().request(5);
        subscriber.getSubscription().cancel();
        subscriber.getSubscription().request(2);
    }
}
