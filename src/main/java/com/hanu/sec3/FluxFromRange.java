package com.hanu.sec3;

import com.hanu.common.Util;
import reactor.core.publisher.Flux;

public class FluxFromRange {
    public static void main(String[] args) {
        Flux.range(1, 10).subscribe(Util.subscriber());
        Flux.range(5, 10).map(i -> "Product - " + i)
                .subscribe(Util.subscriber());
    }
}
