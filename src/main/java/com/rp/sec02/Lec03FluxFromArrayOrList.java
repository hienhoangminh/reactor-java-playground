package com.rp.sec02;

import com.rp.courseUtill.Util;
import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.List;

public class Lec03FluxFromArrayOrList {

    public static void main(String[] args) {
        List<String> strings = Arrays.asList("a", "b", "c");

        Integer[] arr = {2, 5, 7, 8};

        Flux.fromIterable(strings)
                .subscribe(Util.onNext());

        Flux.fromArray(arr)
                .subscribe(Util.onNext());
    }

}
