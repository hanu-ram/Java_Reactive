package com.hanu.sec3.client;

import lombok.extern.slf4j.Slf4j;
import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.LoopResources;

@Slf4j
public abstract class AbstractHttpClient {
    protected static final String BASE_URL = "http://localhost:7070";
    protected final HttpClient client;
    AbstractHttpClient() {
        var loopResources = LoopResources.create("http-client", 1, true);
        client = HttpClient.create().baseUrl(BASE_URL).runOn(loopResources);
    }
}
