package com.rp.sec12.helper;

import com.rp.courseUtill.Util;
import reactor.core.publisher.Mono;
import reactor.util.context.Context;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class BookService {
    private static Map<String, Integer> map = new HashMap<>();

    static{
        map.put("std", 2);
        map.put("prime", 3);
    }

    public static Mono<String> getBook(){
        return Mono.deferContextual(ctx -> {
            if(ctx.get("allow")){
                return Mono.just(Util.faker().book().title());
            }else{
                return Mono.error(new RuntimeException("Not allowed!"));
            }
        }).contextWrite(rateLimitContext());
    }

    public static Function<Context, Context> rateLimitContext(){
        return context -> {
            if(context.hasKey("category")){
               String category = context.get("category").toString();
               Integer attempts = map.get(category);
               if(attempts > 0){
                   map.put(category, attempts - 1);
                   return context.put("allow", true);
               }
            }
            return context.put("allow", false);
        };
    }
}
