package com.hanu.sec09;

import com.hanu.common.Util;
import com.hanu.sec7.client.ExternalServiceClient;
import reactor.core.publisher.Flux;

public class FlatMapAssignment {
    public static void main(String[] args) {
        var client = new ExternalServiceClient();
        Flux.range(1, 10)
                .flatMap(client::getProductById, 3)
                .subscribe(Util.subscriber("sub1"));
        Util.sleepSeconds(5);
       /* for (int i =1; i <= 10; i++) {
            client.getProductById(i).subscribe(Util.subscriber());
        }
        Util.sleepSeconds(5);
    */
    }
}
