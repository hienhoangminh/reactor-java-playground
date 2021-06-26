package com.rp.sec09.assignment;

import com.rp.courseUtill.Util;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Order {
    private String item;
    private double price;
    private String category;
    private int quantity;

    public Order() {
        this.item = Util.faker().book().title();
        this.price = Double.parseDouble(Util.faker().commerce().price());
        this.category = Util.faker().book().genre();
        this.quantity = Util.faker().random().nextInt(1, 10);
    }
}
