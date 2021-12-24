package com.training.spring;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.training.spring.models.Menu;
import com.training.spring.models.MenuResponse;

@RequestMapping("/api/v1/restaurant/menu")
public interface IRestaurantMenuController {

    @PostMapping("/calculate")
    public MenuResponse calculateMenu(@Validated @RequestBody final Menu menu);

}
