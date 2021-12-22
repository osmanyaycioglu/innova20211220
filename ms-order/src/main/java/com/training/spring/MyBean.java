package com.training.spring;

import javax.annotation.PreDestroy;

import org.springframework.stereotype.Component;

@Component
public class MyBean {

    public String hello() {
        return "hello world";
    }

    @PreDestroy
    public void xyz() {
        System.out.println("exting");
    }

}
