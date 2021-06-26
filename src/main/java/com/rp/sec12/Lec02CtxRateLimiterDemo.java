package com.rp.sec12;

import com.rp.courseUtill.Util;
import com.rp.sec12.helper.BookService;
import com.rp.sec12.helper.UserService;
import reactor.util.context.Context;

public class Lec02CtxRateLimiterDemo {
    public static void main(String[] args) {
        BookService.getBook()
                .repeat(4)
                .contextWrite(UserService.userCategoryContext())
                .contextWrite(Context.of("user", "mike"))
                .subscribe(Util.subscriber());
    }
}
