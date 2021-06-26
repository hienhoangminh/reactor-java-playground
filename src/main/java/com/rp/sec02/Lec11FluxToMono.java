package com.rp.sec02;

import com.rp.courseUtill.Util;
import reactor.core.publisher.Flux;

public class Lec11FluxToMono {
    public static void main(String[] args) {
        Flux.range(1, 10)
//                .filter(i -> i> 3)
                .next() // The very first item
                .filter(i -> i > 3) // if it is placed here, our first item is 1 which is smaller than 3 => we will get an Mono.empty()
                .subscribe(Util.onNext(),
                        Util.onError(),
                        Util.onComplete());
    }
}
