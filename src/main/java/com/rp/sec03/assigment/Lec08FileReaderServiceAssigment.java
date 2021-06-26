package com.rp.sec03.assigment;

import com.rp.courseUtill.Util;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Lec08FileReaderServiceAssigment {
    public static void main(String[] args) {
        FileReaderService readerService = new FileReaderService();

        Path path = Paths.get("src/main/resources/sec03/test4.txt");
        readerService
                .read(path)
//                .map(s-> {
//                    Integer integer = Util.faker().random().nextInt(0, 10);
//                    if(integer > 8)
//                        throw new RuntimeException("Oops! Something went wrong!");
//                    return s;
//                })
                .subscribe(Util.subscriber());
    }
}
