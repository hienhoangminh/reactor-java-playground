package com.rp.sec11;

import com.rp.courseUtill.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.time.Duration;

public class Lec07SinkManyMulticastDirectAll {
    public static void main(String[] args) {

        System.setProperty("reactor.bufferSize.small", "16");
        // Handle through which we would push items
//        Sinks.Many<Object> sink = Sinks.many().multicast().directAllOrNothing();

        Sinks.Many<Object> sink = Sinks.many().multicast().directBestEffort();

        // Handler through which subscriber receive item
        Flux<Object> flux = sink.asFlux();

        flux.subscribe(Util.subscriber("sam"));

        // For some reason the pipeline of Mike is too slow to process the data
        flux.delayElements(Duration.ofMillis(200)).subscribe(Util.subscriber("mike"));

        for (int i = 0; i < 100; i++) {
            sink.tryEmitNext(i);
        }

        Util.sleepSeconds(60);
    }
}
