package com.rp.sec05.correction;

import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Objects;

public class OrderService {

    private Flux<PurchaseOrder> flux;

    public Flux<PurchaseOrder> orderStream(){
        if(Objects.isNull(flux)){
            flux = getOrderStream();
        }
        return flux;
    }

    private Flux<PurchaseOrder> getOrderStream(){
        return Flux.interval(Duration.ofSeconds(1))
                .map(i -> new PurchaseOrder())
                .publish()
                .refCount(2);

    }

}
