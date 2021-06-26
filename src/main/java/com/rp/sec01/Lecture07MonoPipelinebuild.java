package com.rp.sec01;

import com.rp.courseUtill.Util;
import reactor.core.publisher.Mono;

public class Lecture07MonoPipelinebuild {

    public static void main(String[] args) {
        getName();
        getName().subscribe(Util.onNext());
        getName();
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
