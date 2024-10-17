package com.hanu.sec3;

import com.hanu.common.Util;
import reactor.core.publisher.Flux;

import java.util.List;

public class FluxFromList {
    public static void main(String[] args) {
        List<Integer> integers = List.of(1, 4, 14, 5, 124, 521);
        Flux.fromIterable(integers)
                .subscribe(Util.subscriber());
        Integer[] nums = {3,4,25,3,5};
        Flux.fromArray(nums).subscribe(Util.subscriber());
    }
}
