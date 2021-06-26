package com.rp.sec02;

import com.rp.courseUtill.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Lec09FluxInterval {
    public static void main(String[] args) {
        // Flux interval will publish item periodically
        // Please note that it is not blocking but interval works as non blocking asynchronously
        // interval also use different thread pool
        Flux.interval(Duration.ofSeconds(1))
                .subscribe(Util.onNext());

        Util.sleepSeconds(5);
    }
}
