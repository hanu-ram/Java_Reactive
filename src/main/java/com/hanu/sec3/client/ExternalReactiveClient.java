package com.hanu.sec3.client;

import com.hanu.sec3.client.AbstractHttpClient;
import reactor.core.publisher.Flux;

public final class ExternalReactiveClient extends AbstractHttpClient {

    public Flux<String> getNames() {
        return client.get()
                .uri("/demo02/name/stream")
                .responseContent()
                .asString();
    }
    public Flux<Integer> getStockPriceStream() {
        return client.get()
                .uri("/demo02/stock/stream")
                .responseContent()
                .asString()
                .map(Integer::parseInt);
    }
}
