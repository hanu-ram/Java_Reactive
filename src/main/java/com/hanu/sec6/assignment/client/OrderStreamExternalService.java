package com.hanu.sec6.assignment.client;

import com.hanu.sec6.assignment.OrderResponseDto;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.util.Objects;

@NoArgsConstructor
@Slf4j
public class OrderStreamExternalService extends AbstractHttpClient {

    private Flux<OrderResponseDto> orderResponseDtoFlux;

    public Flux<OrderResponseDto> orderStreamFlux() {
        if (Objects.isNull(orderResponseDtoFlux)) {
            this.orderResponseDtoFlux = getOrderStream();
            return orderResponseDtoFlux;
        }
        return orderResponseDtoFlux;
    }

    private Flux<OrderResponseDto> getOrderStream() {
        return client.get()
                .uri("/demo04/orders/stream")
                .responseContent()
                .asString()
                .map(string -> string.split(":"))
                .map(this::convertStringToOrderResponseDto)
                .doOnNext(response -> log.info("{}", response))
                .publish()
                .refCount(2);
    }

    private OrderResponseDto convertStringToOrderResponseDto(String[] strings) {
        return OrderResponseDto.builder().item(strings[0])
                .category(strings[1])
                .price(Integer.parseInt(strings[2]))
                .quantity(Integer.parseInt(strings[3]))
                .build();
    }

}
