package com.rp.sec04;

import com.rp.courseUtill.Util;
import reactor.core.publisher.Flux;

public class Lec01bisHandleAssigment {
    public static void main(String[] args) {
        Flux.generate(synchronousSink -> synchronousSink.next(Util.faker().country().name()))
                .map(Object::toString)
                .handle((s, synchronousSink) -> {
                    synchronousSink.next(s);
                    if(s.toLowerCase().equals("canada")){
                        synchronousSink.complete();
                    }
                }).subscribe(Util.subscriber());
    }
}