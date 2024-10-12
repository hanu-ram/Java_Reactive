package com.hanu.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

@Slf4j
@Getter
@RequiredArgsConstructor
public class SubscriberCommonImpl<T> implements Subscriber<T> {
    private Subscription subscription;
    private final String name;

    @Override
    public void onSubscribe(Subscription subscription) {
        this.subscription = subscription;
        subscription.request(Long.MAX_VALUE);
    }

    @Override
    public void onNext(T s) {
        log.info("Subscriber::onNext({}): {}", name, s);
    }

    @Override
    public void onError(Throwable throwable) {
        log.error("Subscriber::onError({}): ", name, throwable);
    }

    @Override
    public void onComplete() {
        log.info("Subscriber::onComplete({}) Completed Successfully", name);
    }
}
