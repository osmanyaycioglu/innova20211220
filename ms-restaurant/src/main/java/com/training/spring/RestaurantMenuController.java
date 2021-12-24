package com.training.spring;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;

import com.training.spring.models.Menu;
import com.training.spring.models.MenuResponse;

@RestController
public class RestaurantMenuController implements IRestaurantMenuController {

    @Value("${server.port}")
    private Integer port;

    @Override
    public MenuResponse calculateMenu(final Menu menu) {
        MenuResponse menuResponseLoc = new MenuResponse();
        menuResponseLoc.setAmount(100);
        menuResponseLoc.setMessage("Menu calculated for " + this.port);
        return menuResponseLoc;
    }

}
