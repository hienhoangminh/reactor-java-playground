package com.rp.sec11;

import com.rp.courseUtill.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

public class Lec06SinkManyMulticastDisableHistory {
    public static void main(String[] args) {
        // Handle through which we would push items
        Sinks.Many<Object> sink = Sinks.many().multicast().directAllOrNothing();

        // Handler through which subscriber receive item
        Flux<Object> flux = sink.asFlux();

        // no subscriber receive these data now
        sink.tryEmitNext("hi");
        sink.tryEmitNext("how are you");

        flux.subscribe(Util.subscriber("sam"));
        flux.subscribe(Util.subscriber("mike"));
        sink.tryEmitNext("?");

        flux.subscribe(Util.subscriber("jake"));
        sink.tryEmitNext("new msg");

    }
}
