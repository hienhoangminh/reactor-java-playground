package com.rp.sec09.secondAssignment;

import com.rp.courseUtill.Util;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Set;

public class Lec06SecondAssignment {
    public static void main(String[] args) {
        Set<String> allowedCategories = Set.of(
                "Kids",
                "Automotive"
        );

        OrderService orderService = new OrderService();
        orderService.orderStream()
                .filter(order -> allowedCategories.contains(order.getCategory()))
                .doOnNext(order -> System.out.println("Before processing: " + order))
                .window(Duration.ofSeconds(2))
                .flatMap(flux -> processOrder(flux))
                .groupBy(i -> i.getCategory())
                .subscribe(gf -> process(gf, gf.key()));

        Util.sleepSeconds(120);

    }

    //Process the order
    private static Flux<PurchaseOrder> processOrder(Flux<PurchaseOrder> flux) {
        return flux
                .doOnNext(order -> {
                    if (order.getCategory().equals("Kids")) {
                        order.setQuantity(order.getQuantity() + 1);
                        order.setPrice(order.getPrice()*0.5);
                        order.setItem("[[ " + order.getItem() + " ]]");
                    }else if(order.getCategory().equals("Automotive")){
                        order.setPrice(order.getPrice() * 1.1);
                        order.setItem("{{ " + order.getItem() + " }}");
                    }
                })
                .doOnComplete(() -> {
                    System.out.println("Saved this batch");
                    System.out.println("----------------");
                });
    }

    // process first the key
    private static void process(Flux<PurchaseOrder> flux, String key){
        // We have only 2 groups
        flux.subscribe(i -> System.out.println("Key: " + key + " , Item : " + i));
    }
}
