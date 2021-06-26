package com.rp.sec02.assigment;

import com.rp.courseUtill.Util;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicReference;

public class StockPriceObserver {
    public static void main(String[] args) {
        // Define the reference
        AtomicReference<Subscription> atomicReference = new AtomicReference<>();

        Flux.range(1, 1000)
                .map(i -> getPrice())
                .log()
                .subscribeWith(new Subscriber<Long>() {
                    @Override
                    public void onSubscribe(Subscription subscription) {
                        System.out.println("Received Sub : " + subscription);
                        // Set the reference
                        atomicReference.set(subscription);
                    }

                    @Override
                    public void onNext(Long aLong) {
                        System.out.println("Value :: " + aLong);
                        if (aLong > 110 || aLong < 90) {
                            atomicReference.get().cancel();
                        }
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

        // Request the reference to get value
        atomicReference.get().request(Long.MAX_VALUE);
    }

    private static long getPrice() {
        Util.sleepSeconds(1);
        return (long) (100 + Math.floor(Math.floor(Math.random() * (31) - 15)));
    }

}
