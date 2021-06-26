package com.rp.sec09.assignment;

import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RevenueService {

    private static Map<String, Double> db = new HashMap<>();

    public RevenueService() {
        db.put("Science fiction", 0.0);
        db.put("Fantasy", 0.0);
        db.put("Suspense/Thriller", 0.0);
    }

    public static Flux<Double> calculateRevenue(List<Order> books) {
        return Flux.create((FluxSink<Double> purchaseOrderFluxSink) -> {
            purchaseOrderFluxSink.next(
                    books.stream()
                            .filter(order -> order.getCategory().equals("Science fiction") ||
                                    order.getCategory().equals("Fantasy") ||
                                    order.getCategory().equals("Suspense/Thriller"))
                            .mapToDouble(
                                    order -> order.getQuantity() * order.getPrice()
                            ).sum()
            );
            purchaseOrderFluxSink.complete();
        }).delayElements(Duration.ofSeconds(1));
    }
}
