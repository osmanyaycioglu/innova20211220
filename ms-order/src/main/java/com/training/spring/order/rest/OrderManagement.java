package com.training.spring.order.rest;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.training.spring.order.rest.models.OrderRestObj;

@RestController
@RequestMapping("/api/v1/order/management")
@Validated
public class OrderManagement {

    @PostMapping("/place")
    public String placeOrder(@Validated @RequestBody final OrderRestObj objParam) {
        return "OK " + objParam;
    }

    @GetMapping("/cancel")
    public String cancelOrder(@NotNull @Positive @RequestParam("id") final Long orderId) {
        return "Order Canceled";
    }


    // Yapma
    @PostMapping("/place/{command}")
    public ResponseEntity<?> placeOrder2(@PathVariable("command") final String command,
                                         final HttpServletRequest hsr) {
        switch (command) {
            case "place":
                String parameterLoc = hsr.getParameter("xyz");
                return ResponseEntity.status(HttpStatus.OK)
                                     .body("test");
            case "add":
                return ResponseEntity.status(HttpStatus.OK)
                                     .body("ok");

            case "cancel":
                return ResponseEntity.status(HttpStatus.OK)
                                     .body("canceled");

            default:
                break;
        }
        return null;
    }


}
