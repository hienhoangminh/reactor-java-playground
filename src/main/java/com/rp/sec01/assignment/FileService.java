package com.rp.sec01.assignment;

import com.rp.courseUtill.Util;
import reactor.core.publisher.Mono;

public class FileService {

    private static final String FIRST_FILE_PATH = "src/main/resources/sec01/test.txt";
    private static final String SECOND_FILE_PATH = "src/main/resources/sec01/test2.txt";
    private static final String CONTENT = "This is the text which is written by Mono";

    public static void main(String[] args) {


        //1 - Test reading file with Mono
        readFile(FIRST_FILE_PATH).subscribe(Util.onNext(),
                Util.onError(),
                Util.onComplete());

        //2 - Write to file with Mono
        writeContent(SECOND_FILE_PATH, CONTENT).subscribe(
                Util.onNext(),
                Util.onError(),
                Util.onComplete()
        );

        //3 - Delete file with Mono
        deleteFile(SECOND_FILE_PATH).subscribe(
                Util.onNext(),
                Util.onError(),
                Util.onComplete()
        );
    }

    private static Mono<String> readFile(String filePath) {
        System.out.println("Reading the file...");
        return Mono.fromSupplier(() -> {
            return FileUtil.readFile(filePath);
        });
    }

    public static Mono writeContent(String path, String content) {
        System.out.println("Writing content to the file...");
        return Mono.fromRunnable(() -> {
            FileUtil.writeContent(path, content);
        });
    }

    public static Mono deleteFile(String path) {
        System.out.println("Deleting file...");
        return Mono.fromRunnable(() -> {
            FileUtil.deleteFile(path);
        });
    }

}
