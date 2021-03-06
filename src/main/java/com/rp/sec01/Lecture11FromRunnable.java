package com.rp.sec01;

import com.rp.courseUtill.Util;
import reactor.core.publisher.Mono;

public class Lecture11FromRunnable {

    public static void main(String[] args) {
        Mono.fromRunnable(timeConsumingProcess())
                .subscribe(Util.onNext(),
                        Util.onError(),
                        () -> {
                            System.out.println("Process is done. Sending emails...");
                        });
    }

    public static Runnable timeConsumingProcess() {
        return () -> {
            Util.sleepSeconds(3);
            System.out.println("Operation completed!");
        };
    }

}
