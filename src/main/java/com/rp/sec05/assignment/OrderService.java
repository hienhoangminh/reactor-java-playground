package com.rp.sec05.assignment;

import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import java.time.Duration;
import java.util.*;

public class OrderService {
    private static Map<Integer, List<Order>> db = new HashMap<>();
    static{
        List<Order> list1 = Arrays.asList(
                new Order(1),
                new Order(2),
                new Order(3)
        );

        List<Order> list2 = Arrays.asList(
                new Order(2),
                new Order(3)
        );
        List<Order> list3 = Arrays.asList(
                new Order(3),
                new Order(4),
                new Order(1)
        );
        List<Order> list4 = Arrays.asList(
                new Order(4)
        );
        db.put(1, list1);
        db.put(2, list2);
        db.put(3, list3);
        db.put(4, list4);
    }

    public static Flux<Order> getOrders(){
        return Flux.create((FluxSink<Order> orderFluxSink) -> {
            db.values().stream()
                    .flatMap(List::stream)
                    .forEach(orderFluxSink::next);
            orderFluxSink.complete();
        }).delayElements(Duration.ofSeconds(1));
    }
}
