package com.rp.sec09.assignment;

import com.rp.courseUtill.Util;
import reactor.core.publisher.Flux;
import java.time.Duration;

public class Lec03BookRevenue {

    public static void main(String[] args) {
        OrderService orderService = new OrderService();
        orderService.orderStream()
                .bufferTimeout(3, Duration.ofSeconds(3)) //wait until 3 orders are generated or after 3 sec, depends on which condition satisfies first
                .doOnNext(l -> System.out.println(l))
                .flatMap(RevenueService::calculateRevenue)
                .subscribe(Util.subscriber());

        Util.sleepSeconds(60);
    }

    private static Flux<String> eventStream(){
        return Flux.interval(Duration.ofMillis(300))
                .map(i -> "event " + i);
    }
}
