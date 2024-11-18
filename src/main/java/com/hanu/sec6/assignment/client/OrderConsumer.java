package com.hanu.sec6.assignment.client;

import com.hanu.sec6.assignment.OrderResponseDto;
import reactor.core.publisher.Flux;

public interface OrderConsumer {
    void consume(OrderResponseDto dto);
    Flux<String> stream();
}
