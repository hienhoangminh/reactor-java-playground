package com.rp.sec01;

import reactor.core.publisher.Mono;

public class Lec02MonoJust {
    public static void main(String[] args) {

        // publisher
        Mono<Integer> just = Mono.just(1);

        //1 - Nothing happens until we susbcribe
        System.out.println(just);

        // Subscribe
        just.subscribe(i -> System.out.println("Received : " + i));
    }
}
