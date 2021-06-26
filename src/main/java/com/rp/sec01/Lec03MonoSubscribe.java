package com.rp.sec01;

import com.rp.courseUtill.Util;
import reactor.core.publisher.Mono;

public class Lec03MonoSubscribe {

    public static void main(String[] args) {
//        Mono<String> mono = Mono.just("ball");

//        mono.subscribe(item -> System.out.println(item),
//                err -> System.out.println(err.getMessage()),
//                () -> System.out.println("Completed!"));

        //onError
        Mono<Integer> mono = Mono.just("ball")
                .map(String::length)
                .map(l -> l / 2);

        mono.subscribe(Util.onNext(),
                Util.onError(),
                Util.onComplete());

    }

}
