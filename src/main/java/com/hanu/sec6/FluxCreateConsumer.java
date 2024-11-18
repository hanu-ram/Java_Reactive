package com.hanu.sec6;

import com.hanu.common.Util;
import com.hanu.sec4.helper.NameGeneratorConsumer;
import reactor.core.publisher.Flux;

/**
 * The issue that discussed in sec4 which is Flux.create() can not have multiple subscribers now if we use share() it can be
 * resolved. If I forget the concept. Try with removing the share method and execute and vice versa
 */
public class FluxCreateConsumer {
    public static void main(String[] args) {
        NameGeneratorConsumer generatorConsumer = new NameGeneratorConsumer();
        Flux<String> share = Flux.create(generatorConsumer).share();
        share.subscribe(Util.subscriber("Sub1"));
        share.subscribe(Util.subscriber("Sub2"));

        for (int i = 0; i < 10; i++) {
            generatorConsumer.generate();
        }
    }
}
