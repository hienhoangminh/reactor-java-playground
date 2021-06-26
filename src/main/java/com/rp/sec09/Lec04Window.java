package com.rp.sec09;

import com.rp.courseUtill.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;

public class Lec04Window {

    private static AtomicInteger atomicInteger = new AtomicInteger(1);

    public static void main(String[] args) {
        eventStream()
                .window(Duration.ofSeconds(2)) // Flux within the Flux
                .flatMap(flux -> saveEvent(flux)) // process the Flux and give complete signal
                .subscribe(Util.subscriber()); // we subscribe to the parent publisher

        Util.sleepSeconds(60);
    }

    private static Flux<String> eventStream(){
        return Flux.interval(Duration.ofMillis(800))
                .map(i -> "event " + i);
    }

    // processor to process the Flux and return on confirmation
    // that it's done
    // we have Flux of Flux, and each Flux is completed after emitting 5 items.
    private static Mono<Integer> saveEvent(Flux<String> flux){
        return flux
                .doOnNext(e -> System.out.println("saving " + e))
                .doOnComplete(() -> {
                    System.out.println("Saved this batch");
                    System.out.println("----------------");
                }).then(Mono.just(atomicInteger.getAndIncrement()));
                //once the Flux complete, notify me about complete signal
    }
}
