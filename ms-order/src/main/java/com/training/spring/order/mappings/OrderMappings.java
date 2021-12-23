package com.training.spring.order.mappings;

import com.training.spring.order.models.Order;
import com.training.spring.order.rest.models.OrderRestObj;

public class OrderMappings {

    public static Order toOrder(final OrderRestObj objParam) {
        Order orderLoc = new Order();
        orderLoc.setName(objParam.getName());
        orderLoc.setSurname(objParam.getSurname());
        orderLoc.setNumber(objParam.getNumber());
        orderLoc.setMeals(objParam.getMeals());
        orderLoc.setDeliveryPeriod(objParam.getDeliveryPeriod());
        return orderLoc;
    }

}
