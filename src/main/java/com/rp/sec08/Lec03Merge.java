package com.rp.sec08;

import com.rp.courseUtill.Util;
import com.rp.sec08.helper.AmericanAirlinesFlights;
import com.rp.sec08.helper.EmiratesFlights;
import com.rp.sec08.helper.QatarFlights;
import reactor.core.publisher.Flux;

public class Lec03Merge {
    public static void main(String[] args) {

        Flux<String> merge = Flux.merge(
           QatarFlights.getFligths(),
           EmiratesFlights.getFligths(),
           AmericanAirlinesFlights.getFligths()
        );

        merge.subscribe(Util.subscriber());

        Util.sleepSeconds(10);

    }
}
