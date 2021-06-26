package com.rp.sec05;

import com.rp.courseUtill.Util;
import reactor.core.publisher.Flux;
import java.time.Duration;
import java.util.stream.Stream;

public class Lec03HotPublish {

    public static void main(String[] args) {
        // share = publish().refCount(1)
        Flux<String> movieStream = Flux.fromStream(() -> getMovie())
                .delayElements(Duration.ofSeconds(2))
                .publish() // ConnectableFlux through which multiple subscribers can connect to the Flux
                .refCount(2); // at least we need n subscriber to make the publisher start emitting the data
        movieStream.subscribe(Util.subscriber("sam"));
        Util.sleepSeconds(5);

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
                "Scene 7",
                "Scene 8",
                "Scene 9",
                "Scene 10");
    }
}
