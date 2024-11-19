package com.hanu.sec09;

import com.hanu.common.Util;
import com.hanu.sec09.services.Order;
import com.hanu.sec09.services.OrderService;
import com.hanu.sec09.services.UserService;
import reactor.core.publisher.Flux;

public class MonoFlatMapMany {
    public static void main(String[] args) {

        Flux<Order> orders = UserService.getUserIdByName("Hanu")
                .flatMapMany(OrderService::getOrdersByUserId); // To convert Mono to Flux we use flatMapMany. flatMap wont work here.

        orders.subscribe(Util.subscriber("sub1"));
        Util.sleepSeconds(4);
    }
}
