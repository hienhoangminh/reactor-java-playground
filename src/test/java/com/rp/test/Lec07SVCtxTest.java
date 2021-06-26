package com.rp.test;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import reactor.test.StepVerifierOptions;
import reactor.util.context.Context;

public class Lec07SVCtxTest {
    @Test
    public void test1(){
        StepVerifier.create(getWelcomeMessage())
                .verifyError(RuntimeException.class);
    }

    @Test
    public void test2(){
        StepVerifierOptions stepVerifierOptions = StepVerifierOptions.create()
                .withInitialContext(Context.of("user", "mike"));

        StepVerifier.create(getWelcomeMessage(), stepVerifierOptions)
                .expectNext("Welcome sam")
                .verifyComplete();
    }

    private static Mono<String> getWelcomeMessage(){
        return Mono.deferContextual(contextView -> {
            // context object set by downstream
            if(contextView.hasKey("user"))
                return Mono.just("Welcome " + contextView.get("user"));
            else
                return Mono.error(new RuntimeException("Unauthenticated"));
        });
    }
}
