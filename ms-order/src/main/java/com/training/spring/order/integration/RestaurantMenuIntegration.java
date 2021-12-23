package com.training.spring.order.integration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.training.spring.order.integration.models.Menu;
import com.training.spring.order.integration.models.MenuResponse;
import com.training.spring.order.models.Order;

import io.github.resilience4j.retry.annotation.Retry;

@Service
public class RestaurantMenuIntegration {

    @Autowired
    private RestTemplate rt;

    @Retry(name = "restaurant-menu")
    public String calculate(final Order orderParam) {
        Menu menu = new Menu();
        menu.setPhoneNumber(orderParam.getNumber());
        menu.setMeals(orderParam.getMeals());
        MenuResponse responseLoc = this.rt.postForObject("http://RESTAURANT/api/v1/restaurant/menu/calculate",
                                                         menu,
                                                         MenuResponse.class);
        return responseLoc.getMessage() + " fiyat : " + responseLoc.getAmount();
    }

}
