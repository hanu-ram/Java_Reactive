package com.hanu.sec3;

import com.hanu.common.Util;
import lombok.SneakyThrows;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.stream.Stream;
@SuppressWarnings("all")
public class FluxFromStream {
    @SneakyThrows
    public static void main(String[] args) {
        List<Integer> integers = List.of(1, 4, 14, 5, 124, 521);
        Stream<Integer> s = integers.stream();
//        Flux<Integer> integerFlux = Flux.fromStream(s);
//        integerFlux.subscribe(Util.subscriber("sub1"));
//        integerFlux.subscribe(Util.subscriber("sub2")); // throws exception because stream is already streamed or consumed and then closed.
        // Inorder to use stream in another flux or another subscriber then we have to use supplier and create new stream of list
        Flux<Integer> integerFlux = Flux.fromStream(() -> integers.stream());
        integerFlux.subscribe(Util.subscriber("sub3"));
        integerFlux.subscribe(Util.subscriber("sub4"));
    }
}
