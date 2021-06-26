package com.rp.sec09.correction;

import com.github.javafaker.Book;
import com.rp.courseUtill.Util;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class BookOrder {
    private String title;
    private String author;
    private String category;
    private double price;

    public BookOrder(){
        Book book = Util.faker().book();
        this.title = book.title();
        this.author = book.author();
        this.category = book.genre();
        this.price = Double.parseDouble(Util.faker().commerce().price());
    }

}
