package com.rp.sec01.assignment;

import com.rp.courseUtill.Util;

public class TestFileServices {

    public static void main(String[] args) {
        FileServices.read("test.txt")
                .subscribe(Util.onNext(), Util.onError(), Util.onComplete());

        FileServices.write("test3.txt", "Written by Mono!")
                .subscribe(Util.onNext(), Util.onError(), Util.onComplete());

        FileServices.delete("test4.txt")
                .subscribe(Util.onNext(), Util.onError(), Util.onComplete());
    }
}
