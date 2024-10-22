package com.hanu.sec3.assignment;

import com.hanu.common.Util;
import com.hanu.sec3.client.ExternalReactiveClient;

public class Assignment {
    public static void main(String[] args) {
        ExternalReactiveClient reactiveClient = new ExternalReactiveClient();
        StockObserverSubscriber subscriber = new StockObserverSubscriber();
        reactiveClient.getStockPriceStream()
                .subscribe(subscriber);
        Util.sleepSeconds(20);
    }
}
