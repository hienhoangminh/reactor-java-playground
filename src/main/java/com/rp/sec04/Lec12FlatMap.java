package com.rp.sec04;

import com.rp.courseUtill.Util;
import com.rp.sec04.helper.OrderService;
import com.rp.sec04.helper.UserService;

public class Lec12FlatMap {

    public static void main(String[] args) {
        UserService.getUsers()
                .flatMap(user -> OrderService.getOrders(user.getUserId())) // we get flux here
//                .filter(p -> p.getPrice() > 10)
                .subscribe(Util.subscriber());

        Util.sleepSeconds(60);
    }
}
