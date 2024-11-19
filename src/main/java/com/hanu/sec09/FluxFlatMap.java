package com.hanu.sec09;

import com.hanu.common.Util;
import com.hanu.sec09.services.UserService;
import reactor.core.publisher.Flux;

public class FluxFlatMap {
    public static void main(String[] args) {

        Flux<Integer> map = UserService.getAllUsers()
                .flatMap(user -> UserService.getUserIdByName(user.name())); // for flux flatmap can be used to convert one flux to another flux. and also to convert one mono to flux.

               map.subscribe(Util.subscriber());
    }
}
