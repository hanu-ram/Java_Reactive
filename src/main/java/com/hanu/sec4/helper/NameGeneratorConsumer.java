package com.hanu.sec4.helper;

import com.hanu.common.Util;
import reactor.core.publisher.FluxSink;

import java.util.function.Consumer;

public class NameGeneratorConsumer implements Consumer<FluxSink<String>> {
    FluxSink<String> color;

    @Override
    public void accept(FluxSink<String> stringFluxSink) {
        color = stringFluxSink;
    }

    public void generate() {
        color.next(Util.faker().color().name());
    }
}
