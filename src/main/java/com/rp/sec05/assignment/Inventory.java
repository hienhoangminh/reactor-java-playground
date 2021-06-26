package com.rp.sec05.assignment;

import com.rp.courseUtill.Util;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Inventory {

    private int productId;
    private String productName;
    private long stock;

    public Inventory(int productId) {
        this.productId = productId;
        this.productName = Util.faker().commerce().productName();
        this.stock = Util.faker().random().nextLong(1000L);
    }
}

