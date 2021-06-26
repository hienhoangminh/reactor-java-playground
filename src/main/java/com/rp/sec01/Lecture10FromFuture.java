package com.rp.sec01;

import com.rp.courseUtill.Util;
import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;

public class Lecture10FromFuture {

    public static void main(String[] args) {
        Mono.fromFuture(getName())
                .subscribe(Util.onNext());
        Util.sleepSeconds(3);
    }

    private static CompletableFuture<String> getName() {
        return CompletableFuture.supplyAsync(() -> Util.faker().name().fullName());
    }
}
