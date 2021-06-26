package com.rp.sec09;

import com.rp.courseUtill.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Lec05GroupBy {
    public static void main(String[] args) {
        Flux.range(1, 30)
                .delayElements(Duration.ofSeconds(1))
                .groupBy(i -> i %2) // key 0,1 ; return Flux<GroupedFlux>
                .subscribe(gf -> process(gf, gf.key())); // what is the key for the Flux

        Util.sleepSeconds(60);

    }

    private static void process(Flux<Integer> flux, int key){
        // We have only 2 groups
        System.out.println("Called"); // Invoked once for each group
        flux.subscribe(i -> System.out.println("Key: " + key + " , Item : " + i));
    }
}
