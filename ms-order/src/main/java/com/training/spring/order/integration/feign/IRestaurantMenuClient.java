package com.training.spring.order.integration.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import com.training.spring.order.integration.models.Menu;
import com.training.spring.order.integration.models.MenuResponse;

@FeignClient("RESTAURANT")
public interface IRestaurantMenuClient {

    @PostMapping("/api/v1/restaurant/menu/calculate")
    public MenuResponse calculateMenu(final Menu menu);

}
