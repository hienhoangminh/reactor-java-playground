package com.rp.sec05.correction;

import reactor.core.publisher.Flux;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class RevenuService {

    private Map<String, Double> db = new HashMap<>();

    public RevenuService() {
        db.put("Kids", 0.0);
        db.put("Automotive", 0.0);
    }

    public Consumer<PurchaseOrder> subscribeOrderStream(){
        return p -> db.computeIfPresent(p.getCategory(), (k, v) ->  v + p.getPrice());
    }

    public Flux<String> revenueStream(){
        return Flux.interval(Duration.ofSeconds(2))
                   .map(i -> db.toString());
    }
}
