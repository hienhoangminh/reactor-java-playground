package com.rp.sec02;

import com.rp.courseUtill.Util;
import reactor.core.publisher.Flux;

public class Lec05FluxFromRange {

    public static void main(String[] args) {
        Flux<Integer> integerFlux = Flux.range(2, 10);

        integerFlux.subscribe(Util.onNext(),
                Util.onError(),
                Util.onComplete());

        integerFlux.subscribe(Util.onNext(),
                Util.onError(),
                Util.onComplete());
    }
}
