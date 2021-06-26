package com.rp.sec09.correction;

import com.rp.courseUtill.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Lec03Assignment {

    public static void main(String[] args) {

        Set<String> allowedCategories = Set.of(
                "Science fiction",
                "Fantasy",
                "Suspense/Thriller"
        );
        bookStream().filter(book -> allowedCategories.contains(book.getCategory()))
                .buffer(Duration.ofSeconds(5))
                .map(list -> revenueCalculateReport(list))
                .subscribe(Util.subscriber());

        Util.sleepSeconds(60);

    }

    private static RevenueReport revenueCalculateReport(List<BookOrder> books){
        Map<String, Double> map = books.stream().collect(
                Collectors.groupingBy(
                        BookOrder::getCategory,
                        Collectors.summingDouble(BookOrder::getPrice)
                )
        );
        return new RevenueReport(map);
    }

    private static Flux<BookOrder> bookStream(){
        return Flux.interval(Duration.ofMillis(200))
                .map(i -> new BookOrder());
    }
}
