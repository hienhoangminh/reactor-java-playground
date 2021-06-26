package com.rp.sec12;

import com.rp.courseUtill.Util;
import reactor.core.publisher.Mono;
import reactor.util.context.Context;

public class Lec01Ctx {
    public static void main(String[] args) {
      // Unless we know the username, otherwise we should not provide the message
      getWelcomeMessage()
//              .contextWrite(Context.of("users","jake")) // replace existing key with new value
              .contextWrite(context -> context.put("user", context.get("user").toString().toUpperCase()))
              .contextWrite(Context.of("user","sam"))
              .subscribe(Util.subscriber());
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
