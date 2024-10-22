package com.hanu.sec4.main;

import com.hanu.common.Util;
import com.hanu.sec4.helper.NameGeneratorConsumer;
import reactor.core.publisher.Flux;

public class FluxCreateConsumer {
    public static void main(String[] args) {
        NameGeneratorConsumer generatorConsumer = new NameGeneratorConsumer();
        Flux.create(generatorConsumer)
                .subscribe(Util.subscriber());

        for (int i = 0; i < 10; i++) {
            generatorConsumer.generate();
        }
    }
}
