package com.rp.sec03;

import com.rp.courseUtill.Util;
import reactor.core.publisher.Flux;

public class Lec02FluxTake {
    public static void main(String[] args) {
        // Take subscribe to range and subscribe subcribe to take
        Flux.range(1, 10)
                .log()
                .take(3)//take upto 3 elements
                //after 3rrd items, subscribe cancels subscription
                .log()
                .subscribe(Util.subscriber());
    }
}
