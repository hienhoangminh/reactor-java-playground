package com.rp.sec01;

import com.rp.courseUtill.Util;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

public class Lecture08Async {

    public static void main(String[] args) {
        getName();
        getName().subscribeOn(Schedulers.boundedElastic())
                .subscribe(Util.onNext());
        getName();

        Util.sleepSeconds(5);
    }

    private static Mono<String> getName() {
        System.out.println("Entering getName method...");
        return Mono.fromSupplier(() -> {
            System.out.println("Generating name...");
            Util.sleepSeconds(3);
            return Util.faker().name().fullName();
        }).map(String::toUpperCase);
    }
}
