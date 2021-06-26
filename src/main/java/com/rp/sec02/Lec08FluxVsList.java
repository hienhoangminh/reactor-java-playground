package com.rp.sec02;

import com.rp.courseUtill.Util;
import com.rp.sec02.helper.NameGenerator;
import reactor.core.publisher.Flux;

import java.util.List;

public class Lec08FluxVsList {
    public static void main(String[] args) {
        //Before 5 seconds, nothing happens.
        // List need to be populated before returning to user
//        List<String> names = NameGenerator.getNames(5);
//        System.out.println(names);

        NameGenerator.getFluxNames(5)
                .subscribe(Util.onNext());

    }


}
