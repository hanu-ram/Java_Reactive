package com.hanu.sec09.services;

import com.hanu.common.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;
import java.util.Map;

public class OrderService {
    public static final Map<Integer, List<Order>> orderDB = Map.of(
            1, List.of(
                    new Order(1, Util.faker().commerce().productName(),Util.faker().random().nextInt(100, 500)),
                    new Order(1, Util.faker().commerce().productName(),Util.faker().random().nextInt(100, 500))
            ),
            2, List.of(
                    new Order(2, Util.faker().commerce().productName(),Util.faker().random().nextInt(100, 500)),
                    new Order(2, Util.faker().commerce().productName(),Util.faker().random().nextInt(100, 500)),
                    new Order(2, Util.faker().commerce().productName(),Util.faker().random().nextInt(100, 500))
            ),
            3, List.of()
    );

    public static Flux<Order> getOrdersByUserId(Integer id) {
        return Flux.fromIterable(orderDB.get(id))
                .delayElements(Duration.ofMillis(500));
    }

}
