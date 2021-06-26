package com.rp.sec02.assigment;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.time.LocalDateTime;
import java.util.concurrent.CountDownLatch;

public class StockPriceAssignment {
    public static void main(String[] args) {

        CountDownLatch latch = new CountDownLatch(1);

        StockPricePublisher.getPrice().subscribeWith(new Subscriber<Integer>() {

            private Subscription subscription;

            @Override
            public void onSubscribe(Subscription subscription) {
                this.subscription = subscription;
                subscription.request(Long.MAX_VALUE);//unbounded
            }

            @Override
            public void onNext(Integer integer) {
                System.out.println("Time : " + LocalDateTime.now() + " -> " + "Price : " + integer);
                if (integer > 110 || integer < 90) {
                    this.subscription.cancel();
                    latch.countDown();
                }


            }

            @Override
            public void onError(Throwable throwable) {
                latch.countDown();
            }

            @Override
            public void onComplete() {
                System.out.println("onComplete");
                latch.countDown();
            }
        });

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
