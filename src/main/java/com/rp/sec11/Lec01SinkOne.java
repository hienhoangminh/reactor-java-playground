package com.rp.sec11;

import com.rp.courseUtill.Util;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

public class Lec01SinkOne {
    public static void main(String[] args) {
        // Mono type of Sink
        Sinks.One<Object> sink = Sinks.one();// through this end, people will emit the value

        Mono<Object> mono = sink.asMono(); // through this end, people will be subscribing

        mono.subscribe(Util.subscriber("sam"));
        mono.subscribe(Util.subscriber("mike"));

//        sink.tryEmitValue("Hi");

//        sink.tryEmitError(new RuntimeException("error"));
        sink.emitValue("Hi", (signalType, emitResult) -> {
            // What is the signal type
            System.out.println(signalType.name());
            System.out.println(emitResult.name());
            return false;
            // In case of error, what should I do
        });
    }
}
