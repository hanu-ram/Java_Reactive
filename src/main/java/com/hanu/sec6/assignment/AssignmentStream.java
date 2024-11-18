package com.hanu.sec6.assignment;

import com.hanu.common.Util;
import com.hanu.sec6.assignment.client.InventoryService;
import com.hanu.sec6.assignment.client.OrderStreamExternalService;
import com.hanu.sec6.assignment.client.RevenueService;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
public class AssignmentStream {

    public static void main(String[] args) {
        OrderStreamExternalService externalService = new OrderStreamExternalService();
        Flux<OrderResponseDto> orderResponseDtoFlux = externalService.orderStreamFlux();
        RevenueService revenueService = new RevenueService();
        InventoryService inventoryService = new InventoryService();
        orderResponseDtoFlux.subscribe(inventoryService::consume);
        orderResponseDtoFlux.subscribe(revenueService::consume);
        inventoryService.stream().subscribe(Util.subscriber("Inventory"));
        revenueService.stream().subscribe(Util.subscriber("Revenue"));
        Util.sleepSeconds(30);
    }

}
