package com.rp.sec07;

import com.rp.courseUtill.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.core.scheduler.Schedulers;

public class Lec06FluxCreateWithOverflow {
    public static void main(String[] args) {

        System.setProperty("reactor.bufferSize.small", "16");

        Flux.create(fluxSink -> {
            for (int i = 0; i < 201 & !fluxSink.isCancelled(); i++) {
                fluxSink.next(i);
                System.out.println("Pushed " + i);
                Util.sleepMillis(1);
            }
            fluxSink.complete();
        }, FluxSink.OverflowStrategy.LATEST)
                .publishOn(Schedulers.boundedElastic())
                .doOnNext(i -> {
                    Util.sleepMillis(10);
                })
                .subscribe(Util.subscriber());

        Util.sleepSeconds(10);
    }
}
