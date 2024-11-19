package com.hanu.sec09;

import com.hanu.common.Util;
import com.hanu.sec09.services.*;
import reactor.core.publisher.Mono;

import java.util.List;

public class Assignment {
    record  UserInformation(Integer userId, String userName, Integer balance, List<Order> orders) {}
    public static void main(String[] args) {
        UserService.getAllUsers()
                .flatMap(Assignment::getUserInformation)
                .subscribe(Util.subscriber());
        Util.sleepSeconds(5);
    }
    private static Mono<UserInformation> getUserInformation(User user) {
        return Mono.zip(
                PaymentService.getUserBalance(user.id()),
                OrderService.getOrdersByUserId(user.id()).collectList()
        ).map(tuple -> new UserInformation(user.id(), user.name(), tuple.getT1(), tuple.getT2()));
    }
}
