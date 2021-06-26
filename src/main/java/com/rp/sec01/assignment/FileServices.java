package com.rp.sec01.assignment;

import com.rp.courseUtill.Util;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileServices {

    private static final Path PATH = Paths.get("src/main/resources/sec01");

    public static Mono<String> read(String fileName) {
        return Mono.fromSupplier(() -> readFile(fileName));
    }

    public static Mono write(String fileName, String content) {
        return Mono.fromRunnable(() -> writeFile(fileName, content));
    }

    public static Mono delete(String fileName) {
        return Mono.fromRunnable(() -> deleteFile(fileName));
    }

    public static void main(String[] args) {
        Mono.fromSupplier(() -> readFile("test.txt")).subscribe(Util.onNext(),
                Util.onError(),
                Util.onComplete());
    }

    private static String readFile(String fileName) {
        try {
            return Files.readString(PATH.resolve(fileName));
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    private static void writeFile(String fileName, String content) {
        try {
            Files.writeString(PATH.resolve(fileName), content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void deleteFile(String fileName) {
        try {
            Files.delete(PATH.resolve(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
