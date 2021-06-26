package com.rp.sec05.assignment;

import com.rp.courseUtill.Util;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Order {
    private int productId;
    private double price;
    private int quantity;

    public Order(int productId) {
        this.productId = productId;
        this.price = Double.parseDouble(Util.faker().commerce().price());
        this.quantity = Util.faker().random().nextInt(1, 10);
    }
}
