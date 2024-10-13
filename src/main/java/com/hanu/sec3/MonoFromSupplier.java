package com.hanu.sec3;

import com.hanu.common.SubscriberCommonImpl;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
public class MonoFromSupplier {
    public static void main(String[] args) {
        var fromSupplier = Mono.fromSupplier(() -> getSum(List.of(1, 2, 3, 4, 5)));
        /*fromSupplier.subscribe(
                new SubscriberCommonImpl<>("MonoFromSupplier")
        );*/
    }
    /** difference between Mono.just() and Mono.fromSupplier() is Mono.just() will execute the method or logic
     * even if there is no subscriber, but Mono.fromSupplier will execute the method or logic only when there is a subscriber
     * Mono.just() is eager and Mono.fromSupplier() is lazy
     * Mono.just() can be used when there is a value in memory
    **/
    private static int getSum(List<Integer> list) {
        return list.stream().mapToInt(Integer::intValue).sum();
    }
}
