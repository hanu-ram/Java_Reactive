package com.hanu.sec1.subscription;

import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

@RequiredArgsConstructor
@Slf4j
public class SubscriptionImpl implements Subscription {
    private boolean isCancelled = false;
    private static final int MAX_ITEMS = 10;

    private final Subscriber<? super String> subscriber;

    private int count = 0;

    private final Faker faker = Faker.instance();

    @Override
    public void request(long requested) {
        if (isCancelled) {
            return;
        }
        if (requested > MAX_ITEMS) {
            subscriber.onError(new IllegalArgumentException("Cannot request more than " + MAX_ITEMS + " items"));
            isCancelled = true;
            return;
        }
        log.info("Subscription::request: {}", requested);
        for (int i = 0; i < requested; i++) {
            if (count == MAX_ITEMS) {
                break;
            }
            subscriber.onNext(faker.internet().emailAddress());
            count++;
        }
        if (count == MAX_ITEMS) {
            log.info("Subscription::request No more data to produce");
            subscriber.onComplete();
        }
    }

    @Override
    public void cancel() {
        log.info("Subscription::cancel Cancelled Successfully");
        isCancelled = true;
    }
}
