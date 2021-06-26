package com.rp.sec05;

import com.rp.courseUtill.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.stream.Stream;

public class Lec03HotPublisherResubscribe {

    public static void main(String[] args) {
        // share = publish().refCount(1)
        Flux<String> movieStream = Flux.fromStream(() -> getMovie())
                .delayElements(Duration.ofSeconds(1))
                .publish() // ConnectableFlux through which multiple subscribers can connect to the Flux
                .refCount(1); // at least we need n subscriber to make the publisher start emitting the data
        movieStream.subscribe(Util.subscriber("sam")); // start streaming immediately when one subscriber subscribe

        // after 7 sec,
        Util.sleepSeconds(10); // after 10 sec, Mike joins

        movieStream.subscribe(Util.subscriber("mike"));
        Util.sleepSeconds(60);

    }

    private static Stream<String> getMovie(){
        System.out.println("Got the movie streaming req");
        return Stream.of(
                "Scene 1",
                "Scene 2",
                "Scene 3",
                "Scene 4",
                "Scene 5",
                "Scene 6",
                "Scene 7");
    }
}
