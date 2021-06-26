package com.rp.sec03;

import com.rp.courseUtill.Util;
import reactor.core.publisher.Flux;

public class Lec03FluxCreateIssueFix {
    public static void main(String[] args) {
        Flux.create(fluxSink -> {
            String country;
            do {
                country = Util.faker().country().name();
                System.out.println("Emitting : " + country);
                fluxSink.next(country);
            } while (!country.equalsIgnoreCase("Canada") && !fluxSink.isCancelled());
            fluxSink.complete();
        })
                .take(3)
                .subscribe(Util.subscriber());
    }
}
