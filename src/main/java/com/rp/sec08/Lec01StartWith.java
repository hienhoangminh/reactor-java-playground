package com.rp.sec08;

import com.rp.courseUtill.Util;
import com.rp.sec08.helper.NameGenerator;

public class Lec01StartWith {
    public static void main(String[] args) {

        NameGenerator generator = new NameGenerator();
        generator.generateName()
                .take(2)
                .subscribe(Util.subscriber("sam"));
        generator.generateName()
                .take(2)
                .subscribe(Util.subscriber("mike"));
        generator.generateName()
                .take(3)
                .subscribe(Util.subscriber("jake"));
        generator.generateName()
                .filter(n -> n.startsWith("A"))
                .take(1)
                .subscribe(Util.subscriber("marshal"));
    }
}
