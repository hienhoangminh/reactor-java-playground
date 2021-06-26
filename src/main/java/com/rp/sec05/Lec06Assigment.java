package com.rp.sec05;

import com.rp.courseUtill.Util;
import com.rp.sec05.correction.InventoryService;
import com.rp.sec05.correction.OrderService;
import com.rp.sec05.correction.RevenuService;

public class Lec06Assigment {
    public static void main(String[] args) {
        OrderService orderService = new OrderService();
        RevenuService revenuService = new RevenuService();
        InventoryService inventoryService = new InventoryService();

        orderService.orderStream().subscribe(revenuService.subscribeOrderStream());
        orderService.orderStream().subscribe(inventoryService.subscribeOrderStream());

        inventoryService.inventoryStream()
                .subscribe(Util.subscriber("inventory"));

        revenuService.revenueStream()
                .subscribe(Util.subscriber("revenue"));

        Util.sleepSeconds(60);
    }
}
