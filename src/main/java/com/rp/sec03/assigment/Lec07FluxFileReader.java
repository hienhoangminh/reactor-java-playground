package com.rp.sec03.assigment;

import com.rp.courseUtill.Util;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Lec07FluxFileReader {

    private static final Path PATH = Paths.get("src/main/resources/sec03");

    public static void main(String[] args) {
        Flux.generate(
                () -> 1,
                (counter, sink) -> {
                    List<String> content = new ArrayList<>();
                    try {
                        content = Files.readAllLines(PATH.resolve("test4.txt"));
                        String line = content.get(counter);
                        sink.next(line);
                        if (counter >= content.size() - 1) {
                            sink.complete();
                        }
                    } catch (IOException e) {
                        sink.error(e.getCause());
                    }

                    return counter + 1;
                }
        ).take(3).subscribe(Util.subscriber());
    }
}
