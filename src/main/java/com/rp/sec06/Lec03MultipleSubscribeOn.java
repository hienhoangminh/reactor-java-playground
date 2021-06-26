package com.rp.sec06;

import com.rp.courseUtill.Util;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class Lec03MultipleSubscribeOn {
    public static void main(String[] args) {
        Flux<Object> flux = Flux.create(fluxSink -> {
            printThreadName("created");
            fluxSink.next(1);
        }).subscribeOn(Schedulers.newParallel("test"))
        .doOnNext(i -> printThreadName("next " + i));

        Runnable runnable = () -> flux
           .doFirst(() -> printThreadName("first2"))
           .subscribeOn(Schedulers.boundedElastic())
           .doFirst(() -> printThreadName("first1"))
              // parallel: limited number of threads. 4 cores CPU machines -> 4 threads. One thread for CPU bounded.
              // boundedElastic(): 10 times more of threads that parallel: same example we have 40 threads. -> Best option for time-consuming tasks
           .subscribe(v -> printThreadName("sub " + v));

        for (int i = 0; i < 2; i++) {
            new Thread(runnable).start();
        }

      Util.sleepSeconds(5);
    }

    private static void printThreadName(String msg){
        System.out.println(msg + "\t\t: Thread : " + Thread.currentThread().getName());
    }
}
