package com.rp.sec01;

import com.rp.courseUtill.Util;
import reactor.core.publisher.Mono;

import java.util.concurrent.Callable;

public class Lec06MonoFromCallable {

    public static void main(String[] args) {
        Callable<String> callable = () -> getName();

        // When we want to calculate for the new data
        Mono<String> mono = Mono.fromCallable(() -> getName());
        mono.subscribe(Util.onNext());
    }

    private static String getName() {
        System.out.println("Generating name...");
        return Util.faker().name().fullName();
    }
}
