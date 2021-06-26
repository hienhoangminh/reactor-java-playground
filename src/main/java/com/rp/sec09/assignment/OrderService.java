package com.rp.sec09.assignment;

import reactor.core.publisher.Flux;
import java.time.Duration;
import java.util.Objects;

public class OrderService {

    private Flux<Order> flux;

    public Flux<Order> orderStream(){
        if(Objects.isNull(flux)){
            flux = getOrderStream();
        }
        return flux;
    }

    private Flux<Order> getOrderStream(){
        return Flux.interval(Duration.ofSeconds(1))
                .map(i -> new Order());
    }
}
