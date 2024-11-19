package com.hanu.sec09.services;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

public class UserService {
    public static final Map<String, Integer> userDB = Map.of(
            "Vind", 1,
            "Hanu", 2,
            "Mike", 3
    );

    public static Flux<User> getAllUsers() {
        return Flux.fromIterable(userDB.entrySet())
                .map(user -> new User(user.getValue(), user.getKey()));
    }

    public static Mono<Integer> getUserIdByName(String userName) {
        return Mono.fromSupplier(() -> userDB.get(userName));
    }

}
