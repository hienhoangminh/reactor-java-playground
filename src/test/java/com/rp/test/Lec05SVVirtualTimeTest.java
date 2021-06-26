package com.rp.test;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.time.Duration;

public class Lec05SVVirtualTimeTest {
    @Test
    public void test1(){
        // We assume that 30 s passed
        StepVerifier.withVirtualTime(() -> timeConsumingFlux())
                .thenAwait(Duration.ofSeconds(30))
                .expectNext("1a", "2a", "3a", "4a")
                .verifyComplete();
    }

    @Test
    public void test2(){
        // We assume that 30 s passed
        // First 5 s there is no data emitted
        // expectSubscription: we assume that there is a subscription
        StepVerifier.withVirtualTime(() -> timeConsumingFlux())
                .expectSubscription()
                .expectNoEvent(Duration.ofSeconds(6))
                .thenAwait(Duration.ofSeconds(30))
                .expectNext("1a", "2a", "3a", "4a")
                .verifyComplete();
    }


    private Flux<String> timeConsumingFlux(){
        return Flux.range(1, 4)
                .delayElements(Duration.ofSeconds(5))
                .map(i -> i + "a");
    }
}
