package com.rp.sec04;

import com.rp.courseUtill.Util;
import reactor.core.publisher.Flux;

public class AssignmentFluxGenerate {
    public static void main(String[] args) {
        Flux.generate(synchronousSink -> {
            String country = Util.faker().country().name();
            System.out.println("Emitting..." + country);
            synchronousSink.next(country);

        }).handle((country, synchronousSink) -> {
            if(country.toString().toLowerCase().equals("canada")){
                synchronousSink.complete();
            }else
                synchronousSink.next(country);
        }).subscribe(Util.subscriber());
    }
}
