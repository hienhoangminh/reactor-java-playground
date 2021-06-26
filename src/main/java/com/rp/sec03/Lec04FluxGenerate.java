package com.rp.sec03;

import com.rp.courseUtill.Util;
import reactor.core.publisher.Flux;

public class Lec04FluxGenerate {
    public static void main(String[] args) {
        Flux.generate(synchronousSink -> {
            System.out.println("Emitting...");
            synchronousSink.next(Util.faker().country().name());
//            synchronousSink.next(Util.faker().country().name());
        }).log()
                .take(2)
                .log()
                .subscribe(Util.subscriber());
    }
}
