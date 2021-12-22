package com.training.spring.order.rest;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/order/status")
public class OrderStatus {

    @GetMapping("/get/one")
    public String getStatusOfOrder(@RequestParam("id") final Long orderId) {
        return "order " + orderId + " OK";
    }

    @GetMapping("/get/single/{oid}")
    public String getSingleStatusOfOrder(@PathVariable("id") final Long orderId) {
        return "order " + orderId + " OK";
    }

    @GetMapping("/get/all")
    public List<String> getAll() {
        return null;
    }

}
