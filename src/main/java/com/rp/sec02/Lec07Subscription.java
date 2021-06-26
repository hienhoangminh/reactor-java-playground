package com.rp.sec02;

import com.rp.courseUtill.Util;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;

import java.util.concurrent.atomic.AtomicReference;

public class Lec07Subscription {
    public static void main(String[] args) {

        // Define the reference
        AtomicReference<Subscription> atomicReference = new AtomicReference<>();

        Flux.range(1, 20)
                .log()
                .subscribeWith(new Subscriber<Integer>() {
                    @Override
                    public void onSubscribe(Subscription subscription) {
                        System.out.println("Received Sub : " + subscription);
                        // Set the reference
                        atomicReference.set(subscription);
                    }

                    @Override
                    public void onNext(Integer integer) {
                        System.out.println("onNext : " + integer);

                    }

                    @Override
                    public void onError(Throwable throwable) {
                        System.out.println("onError : " + throwable.getMessage());

                    }

                    @Override
                    public void onComplete() {
                        System.out.println("onComplete");

                    }
                });

        Util.sleepSeconds(3);
        // Request the reference to get value
        atomicReference.get().request(3);
        Util.sleepSeconds(5);
        atomicReference.get().request(3);
        Util.sleepSeconds(5);
        System.out.println("Going to cancel!");
        //Cancel the subscription
        atomicReference.get().cancel();
        Util.sleepSeconds(3);
        atomicReference.get().request(4);

    }
}
