package com.rp.sec08.assignment;

import com.rp.courseUtill.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class Lec06Assignment {
    public static void main(String[] args) {
        Flux.combineLatest(monthStream(), demandStream(), (v1, v2) -> {
            return v1*v2;
        })
                .subscribe(Util.subscriber());
        Util.sleepSeconds(20);
    }

    private static Flux<Long> monthStream(){
        return Flux.interval(Duration.ZERO, Duration.ofSeconds(1))
                .map(i -> 10000 - (i*100));
    }

    private static Flux<Double> demandStream(){
        return Flux.interval(Duration.ofSeconds(3))
                .map(i -> Util.faker().random().nextInt(80, 120)/100d)
                .startWith(1d);
    }
}
