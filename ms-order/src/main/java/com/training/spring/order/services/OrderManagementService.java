package com.training.spring.order.services;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.spring.order.data.OrderStorage;
import com.training.spring.order.integration.NotifyClient;
import com.training.spring.order.integration.RestaurantMenuIntegration;
import com.training.spring.order.models.Order;

@Service
public class OrderManagementService {

    @Autowired
    private OrderStorage              orderStorage;

    @Autowired
    private RestaurantMenuIntegration rmi;

    @Autowired
    private NotifyClient              nc;

    private final AtomicLong          index = new AtomicLong();

    public String placeOrder(final Order orderParam) {
        orderParam.setOrderId(this.index.incrementAndGet());
        this.orderStorage.add(orderParam);
        String calculateLoc = this.rmi.calculate(orderParam);
        this.nc.notifyWithSMS(orderParam,
                              calculateLoc);
        this.nc.notifyWithMail(orderParam,
                               calculateLoc);
        return calculateLoc;
    }
}
