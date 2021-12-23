package com.training.spring.order.retry;

import java.util.function.Predicate;

public class RestaurantMenuResultHandler implements Predicate<String> {

    @Override
    public boolean test(final String tParam) {
        System.out.println("Result : " + tParam);
        return false;
    }

}
