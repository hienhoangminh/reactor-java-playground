package com.rp.sec04;

import com.rp.courseUtill.Util;
import reactor.core.publisher.Flux;

public class Lec04LimitRate {
    public static void main(String[] args) {
        Flux.range(1, 1000)// in real life, it could be from 1 thread
                .log()
                .limitRate(100, 0)//we need to have
                .subscribe(Util.subscriber()); // it comes from other thread
    }
}
