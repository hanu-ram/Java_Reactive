package com.hanu.sec3;

import com.hanu.common.SubscriberCommonImpl;
import com.hanu.common.Util;
import reactor.core.publisher.Mono;

import java.util.List;

public class MonoFromCallable {
    public static void main(String[] args) {
        var fromCallable = Mono.fromCallable(() -> getSum(List.of(1, 2, 3, 4, 5)));
        fromCallable.subscribe(
                Util.subscriber("MonoFromCallable")
        );
        var fromSupplier = Mono.fromSupplier(() -> {
            try {
                return getSum(List.of(1, 2, 3, 4, 5));
            } catch (Exception e) {
                throw new IllegalArgumentException(e);
            }
        });
        fromSupplier.subscribe(new SubscriberCommonImpl<>("MonoFromSupplier"));
    }
    /** difference Mono.fromSupplier() is and Mono.fromCallable() is Callable can handle checked Exceptions
     * but Supplier cannot handle checked Exceptions, it can handle only unchecked Exceptions
     **/
    private static int getSum(List<Integer> list) throws Exception {
        return list.stream().mapToInt(Integer::intValue).sum();
    }
}
