package com.rp.sec02;

import com.rp.courseUtill.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Lec10FLuxFromMono {
    public static void main(String[] args) {
        Mono<String> mono = Mono.just("a");
        Flux<String> flux = Flux.from(mono);
        flux.subscribe(Util.onNext());
    }

}
