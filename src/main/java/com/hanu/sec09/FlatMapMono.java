package com.hanu.sec09;

import com.hanu.common.Util;
import com.hanu.sec09.services.PaymentService;
import com.hanu.sec09.services.UserService;
import reactor.core.publisher.Mono;

public class FlatMapMono {
    public static void main(String[] args) {

        UserService.getUserIdByName("Hanu")
                .map(id -> Mono.fromSupplier(() -> id + " is the user id")) // To get the actual value in Mono we have inner mono someone should subscribe to get the value.
                .subscribe(Util.subscriber());

        Mono<Integer> bal = UserService.getUserIdByName("Vind")
                .flatMap(PaymentService::getUserBalance);

        bal.subscribe(Util.subscriber());
    }
}
