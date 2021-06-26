package com.rp.sec03;

import com.rp.courseUtill.Util;
import reactor.core.publisher.Flux;

public class Lec05FluxWithCounter {
    public static void main(String[] args) {
        Flux.generate(
                () -> 1,
                (counter, sink) -> {
                    String country = Util.faker().country().name();
                    sink.next(country);
                    if (counter >= 10 || country.toLowerCase().equalsIgnoreCase("canada")) {
                        sink.complete();
                    }
                    return counter + 1;
                }
        ).subscribe(Util.subscriber());
    }
}
