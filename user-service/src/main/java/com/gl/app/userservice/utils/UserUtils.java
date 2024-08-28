package com.gl.app.userservice.utils;

import java.util.concurrent.atomic.AtomicInteger;

public class UserUtils {
    private static final AtomicInteger counter1 = new AtomicInteger(100);
    private static final AtomicInteger counter2 = new AtomicInteger(100);
    public static String generateUserId(String prefix) {
        int currentValue = counter1.incrementAndGet();
        return prefix + currentValue;
    }
    public static String generateWishlistId(String prefix) {
        int currentValue = counter2.incrementAndGet();
        return prefix + currentValue;
    }
}
