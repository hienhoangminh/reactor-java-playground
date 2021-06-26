package com.rp.sec02;

import com.rp.courseUtill.Util;
import reactor.core.publisher.Flux;

public class Lec06FluxLog {

    public static void main(String[] args) {
        Flux<String> nameFlux = Flux.range(1, 10)
                .log()
                .map(i -> Util.faker().name().fullName());

        nameFlux.subscribe(Util.onNext(),
                Util.onError(),
                Util.onComplete());
    }
}
