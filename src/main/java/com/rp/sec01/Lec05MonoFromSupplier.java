package com.rp.sec01;

import com.rp.courseUtill.Util;
import reactor.core.publisher.Mono;

public class Lec05MonoFromSupplier {

    public static void main(String[] args) {
        // Use just only when we have data already
//        Mono<String> mono = Mono.just(getName());

        // When we want to calculate for the new data
        Mono<String> mono = Mono.fromSupplier(() -> getName());
        mono.subscribe(Util.onNext(),
                Util.onError(),
                Util.onComplete());

    }

    private static String getName() {
        System.out.println("Generating name...");
        return Util.faker().name().fullName();
    }
}
