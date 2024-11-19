package com.hanu.sec09;

import com.hanu.common.Util;
import com.hanu.sec7.client.ExternalServiceClient;
import reactor.core.publisher.Flux;

public class ConcatMap {
    public static void main(String[] args) {
        var client = new ExternalServiceClient();
        Flux.range(1, 10)
                .concatMap(client::getProductById) // its equivalent to flatMap(client::getProductById, 1). It sends the request one by one.
                .subscribe(Util.subscriber("sub1"));
        Util.sleepSeconds(5);
    }
}
