package com.training.spring.order.integration;

import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;

import io.github.resilience4j.retry.annotation.Retry;

@Component
public class MyRetryTest {

    private int counter = 0;


    @Retry(name = "restaurant-menu")
    public String test() {
        this.counter++;
        if ((this.counter % 3) == 0) {
            throw new ResourceAccessException("test");
        }
        return "OK";
    }

}
