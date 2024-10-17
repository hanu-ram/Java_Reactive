package com.hanu.sec3.helper;

import com.hanu.common.Util;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.stream.IntStream;

public class Sec3Helper {

    public static List<String> getNamesUsingList(int count) {
        return IntStream.rangeClosed(1, count)
                .mapToObj(i -> generateName())
                .toList();
    }

    public static Flux<String> getNamesUsingFlux(int count) {
        return Flux.range(1, count)
                .map(i -> generateName());
    }

    private static String generateName() {
        Util.sleepSeconds(1);
        return Util.faker().name().firstName();
    }
}
