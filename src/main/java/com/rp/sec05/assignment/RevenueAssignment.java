package com.rp.sec05.assignment;

import com.rp.courseUtill.Util;

public class RevenueAssignment {
    public static void main(String[] args) {
        OrderService.getOrders()
                .flatMap(order -> InventoryService.updateInventory(order))
                .subscribe(Util.subscriber("Inventory"));

        OrderService.getOrders()
                .flatMap(order -> RevenueService.calculateRevenue(order))
                .subscribe(Util.subscriber("Revenue"));

        Util.sleepSeconds(10);
    }
}
