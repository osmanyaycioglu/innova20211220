package com.training.spring.test;

import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;

@Component
public class MyTest {

    private int counter = 0;


    @Retry(name = "restaurant-menu")
    public String test() {
        this.counter++;
        if ((this.counter % 3) == 0) {
            throw new ResourceAccessException("test");
        }
        return "OK";
    }

    @Retry(name = "restaurant-menu")
    @CircuitBreaker(name = "restaurant-menu-cb")
    public String test2() {
        this.counter++;
        if ((this.counter % 3) == 0) {
            throw new ResourceAccessException("test");
        }
        return "OK";
    }

}
