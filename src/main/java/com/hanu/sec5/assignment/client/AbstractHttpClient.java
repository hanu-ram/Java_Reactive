package com.hanu.sec5.assignment.client;

import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.LoopResources;

public abstract class AbstractHttpClient {
    protected static final String BASE_URL = "http://localhost:7070";

    protected final HttpClient client;

    AbstractHttpClient() {
        client = HttpClient.create().baseUrl(BASE_URL).runOn(LoopResources.create("http-client", 1, true));
    }

}
