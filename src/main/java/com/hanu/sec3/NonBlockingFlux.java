package com.hanu.sec3;

import com.hanu.common.Util;
import com.hanu.sec3.client.ExternalReactiveClient;

public class NonBlockingFlux {
    public static void main(String[] args) {
        ExternalReactiveClient reactiveClient = new ExternalReactiveClient();
        reactiveClient.getNames()
                .subscribe(Util.subscriber());
        Util.sleepSeconds(5);
    }
}
