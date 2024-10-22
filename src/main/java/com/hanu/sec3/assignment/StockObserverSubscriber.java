package com.hanu.sec3.assignment;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

@Slf4j
@Getter
public class StockObserverSubscriber implements Subscriber<Integer> {
    private int balance = 1000;
    private int quantity = 0;
    private Subscription subscription;


    @Override
    public void onSubscribe(Subscription s) {
        s.request(Long.MAX_VALUE);
        subscription = s;
    }

    @Override
    public void onNext(Integer price) {
        log.info("onNext: {}", price);
        if (price < 90 && balance >= price) {
            quantity++;
            balance -= price;
            log.info("Quantity: {}. Balance: {}", quantity, balance);
        } else if (price > 110 && quantity > 0) {
            subscription.cancel();
            log.info("Price crossed above 110, thus selling the stocks at {}", price);
            balance = balance + (quantity * price);
            quantity = 0;
            log.info("Quantity: {}. Balance: {}. Profit: {}", quantity, balance, balance - 1000);
        }
    }

    @Override
    public void onError(Throwable t) {
        log.error("onError: {}", t.getMessage());
        subscription.cancel();
    }

    @Override
    public void onComplete() {
        log.info("onComplete: Completed Successfully");
    }
}
