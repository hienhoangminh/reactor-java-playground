package com.rp.sec05.assignment;

import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import java.time.Duration;

public class RevenueService {

    public static Flux<Double> calculateRevenue(Order order){
        return Flux.create((FluxSink<Double> revenueFluxSink) -> {
            revenueFluxSink.next(order.getPrice() * order.getQuantity());
            revenueFluxSink.complete();
        }).delayElements(Duration.ofSeconds(1));
    }
}
