package com.rp.sec05.assignment;

import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import java.time.Duration;
import java.util.*;

public class InventoryService {

    private static Map<Integer, Inventory> db = new HashMap<>();
    static{
        Inventory product1 = new Inventory(1);
        Inventory product2 = new Inventory(2);
        Inventory product3 = new Inventory(3);
        Inventory product4 = new Inventory(4);

        db.put(1, product1);
        db.put(2, product2);
        db.put(3, product3);
        db.put(4, product4);
    }

    public static Flux<Inventory> updateInventory(Order order){
        return Flux.create((FluxSink<Inventory> inventoryFluxSink) -> {
            Inventory product = db.get(order.getProductId());
            System.out.println("Before updating... " + db.get(order.getProductId()));
            if(product.getStock() > order.getQuantity()){
                product.setStock(product.getStock() - order.getQuantity());
                inventoryFluxSink.next(product);
            }
            inventoryFluxSink.complete();
        }).delayElements(Duration.ofSeconds(1));
    }
}
