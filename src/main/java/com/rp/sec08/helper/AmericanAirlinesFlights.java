package com.rp.sec08.helper;

import com.rp.courseUtill.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class AmericanAirlinesFlights {

    public static Flux<String> getFligths(){
        return Flux.range(1, Util.faker().random().nextInt(1, 10))
                .delayElements(Duration.ofSeconds(1))
                .map(i -> "AA " + Util.faker().random().nextInt(100, 999))
                .filter(i -> Util.faker().random().nextBoolean());
    }

}
