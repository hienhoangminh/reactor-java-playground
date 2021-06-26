package com.rp.sec03;

import com.rp.courseUtill.Util;
import reactor.core.publisher.Flux;

public class Lec01FluxCreate {

//    public static void main(String[] args) {
//        Flux.create(fluxSink -> {
//            fluxSink.next(1);
//            fluxSink.next(2);
//            fluxSink.next(3);
//            fluxSink.next(4);
//            fluxSink.complete();
//        }).subscribe(Util.subscriber());
//    }

    public static void main(String[] args) {
        Flux.create(fluxSink -> {
            String country;
            do {
                country = Util.faker().country().name();
                fluxSink.next(country);
            } while (!country.equalsIgnoreCase("Canada"));
//            for (int i = 0; i < 10; i++) {
//              fluxSink.next(Util.faker().country().name());
//            }
            fluxSink.complete();
        }).subscribe(Util.subscriber());
    }
}
