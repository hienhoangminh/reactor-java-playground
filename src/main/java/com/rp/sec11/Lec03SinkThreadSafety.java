package com.rp.sec11;

import com.rp.courseUtill.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class Lec03SinkThreadSafety {
    public static void main(String[] args) {
        // Handle through which we would push items
        Sinks.Many<Object> sink = Sinks.many().unicast().onBackpressureBuffer();

        // Handler through which subscriber receive item
        Flux<Object> flux = sink.asFlux();

        List<Object> list = new ArrayList<>();

        flux.subscribe(list::add);

        // We are trying to emit via multiple threads.
        // But some thread also gives you that result.
        // So we need to check whatever we are trying to emit is sucessfull or not.
//        for (int i = 0; i < 1000; i++) {
//            final int j = i;
//            CompletableFuture.runAsync(() -> {
//                sink.tryEmitNext(j);
//
//            });
//        }

        for (int i = 0; i < 1000; i++) {
            final int j = i;
            CompletableFuture.runAsync(() -> {
                sink.emitNext(j,(s,e) -> true);

            });
        }

        Util.sleepSeconds(3);

        System.out.println(list.size());

    }
}
