package com.hanu.sec5.assignment;

import com.hanu.common.Util;
import com.hanu.sec5.assignment.client.ReactiveHttpClientImpl;

import java.time.Duration;

public class Assignment {
    public static void main(String[] args) {
        ReactiveHttpClientImpl client = new ReactiveHttpClientImpl();
        int id = 4;
        client.getProductNameById(id)
                .timeout(Duration.ofSeconds(2), client.fallbackForTimeout(id))
                .switchIfEmpty(client.fallbackForEmpty(id))
                .subscribe(Util.subscriber());

        Util.sleepSeconds(3);
    }
}
