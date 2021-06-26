package com.rp.sec08;

import com.rp.courseUtill.Util;
import com.rp.sec08.helper.NameGenerator;
import reactor.core.publisher.Flux;

public class Lec02Concat {
    public static void main(String[] args) {

        Flux<String> flux1 = Flux.just("a", "b");
        Flux<String> flux2 = Flux.just("c", "d", "e");
        Flux<String> flux3 = Flux.error(new RuntimeException("oops"));

//        Flux<String> flux = flux1.concatWith(flux2);
//        Flux<String> flux = Flux.concat(flux1, flux3, flux2);// will be stop due to error

        Flux<String> flux = Flux.concatDelayError(flux1, flux3, flux2);// will be stop due to error

        flux.subscribe(Util.subscriber());

    }
}
