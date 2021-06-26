package com.rp.sec08;

import com.rp.courseUtill.Util;
import reactor.core.publisher.Flux;

public class Lec04Zip {
    public static void main(String[] args) {
        Flux.zip(getBody(), getEngine() , getTires())
//                .doOnNext(tuple -> System.out.println(tuple.getT1())) // can access to tuple by method get
                .subscribe(Util.subscriber());

        Util.sleepSeconds(10);

    }
    private static Flux<String> getBody(){
        return Flux.range(1, 5)
                .map(i -> "body");
    }

    private static Flux<String> getEngine(){
        return Flux.range(1, 2)
                .map(i -> "engine");
    }

    private static Flux<String> getTires(){
        return Flux.range(1, 6)
                .map(i -> "tires");
    }
}
