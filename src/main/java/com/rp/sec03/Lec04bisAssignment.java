package com.rp.sec03;

import com.rp.courseUtill.Util;
import reactor.core.publisher.Flux;

public class Lec04bisAssignment {
    public static void main(String[] args) {
        Flux.generate(synchronousSink -> {
            String country;
            do {
                country = Util.faker().country().name();
                System.out.println("Emitting : " + country);
                synchronousSink.next(country);
            } while (!country.equalsIgnoreCase("canada"));
        }).subscribe(Util.subscriber());
    }
}
