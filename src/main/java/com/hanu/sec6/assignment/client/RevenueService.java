package com.hanu.sec6.assignment.client;

import com.hanu.sec6.assignment.OrderResponseDto;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class RevenueService implements OrderConsumer {
    private Map<String, Integer> db = new HashMap<>();
    @Override
    public void consume(OrderResponseDto dto) {
        Integer currentRevenue = db.getOrDefault(dto.getCategory(), 0);
        Integer updatedRevenue = currentRevenue + dto.getPrice();
        db.put(dto.getCategory(), updatedRevenue);
    }

    @Override
    public Flux<String> stream() {
        return Flux.interval(Duration.ofSeconds(2))
                .map(d -> db.toString());
    }
}
