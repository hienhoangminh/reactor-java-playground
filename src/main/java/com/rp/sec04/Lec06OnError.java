package com.rp.sec04;

import com.rp.courseUtill.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

// Build high resilient pipeline
// We don't want to handle the unexpected exception but with fallback plan
public class Lec06OnError {

    public static void main(String[] args) {

        Flux.range(1, 10)
                .log()
                .map(i -> 10/(5 - i))
//                .onErrorReturn(-1)
//                .onErrorResume(e -> fallback())
                .onErrorContinue((err,obj) -> {
                    // we don't do anything here.
                })
                .subscribe(Util.subscriber());
    }

    private static Mono<Integer> fallback(){
        return Mono.fromSupplier(() -> Util.faker().random().nextInt(100, 200));
    }
}
