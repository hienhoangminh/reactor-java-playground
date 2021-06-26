package com.rp.sec11;

import com.rp.courseUtill.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

public class Lec05SinkManyMulticastBuffer {
    public static void main(String[] args) {
        // Handle through which we would push items
        Sinks.Many<Object> sink = Sinks.many().multicast().onBackpressureBuffer();

        // Handler through which subscriber receive item
        Flux<Object> flux = sink.asFlux();


        sink.tryEmitNext("hi");
        sink.tryEmitNext("how are you");

        // When there are no subscribers, emitted data is actually stored in the queue
        flux.subscribe(Util.subscriber("sam")); // giving data in the Queue to the first subscriber
        flux.subscribe(Util.subscriber("mike"));
        sink.tryEmitNext("?");

        flux.subscribe(Util.subscriber("jake"));
        sink.tryEmitNext("new msg");

    }
}
