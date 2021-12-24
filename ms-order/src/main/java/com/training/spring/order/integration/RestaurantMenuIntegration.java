package com.training.spring.order.integration;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import com.training.error.HttpFeignException;
import com.training.spring.order.integration.feign.IRestaurantMenuClient;
import com.training.spring.order.integration.models.Menu;
import com.training.spring.order.integration.models.MenuResponse;
import com.training.spring.order.models.Order;

import io.github.resilience4j.retry.Retry.Metrics;
import io.github.resilience4j.retry.RetryRegistry;
import io.github.resilience4j.retry.annotation.Retry;

@Service
public class RestaurantMenuIntegration {

    @Autowired
    private RestTemplate          rt;

    private final RetryRegistry   rr;

    @Autowired
    private IRestaurantMenuClient rmc;

    @Autowired
    private EurekaClient          ec;

    private RestTemplateBuilder   restTemplateBuilder;

    public RestaurantMenuIntegration(final RetryRegistry rrParam) {
        super();
        this.rr = rrParam;
        io.github.resilience4j.retry.Retry retryLoc = this.rr.retry("restaurant-menu");
        retryLoc.getEventPublisher()
                .onRetry(e -> System.out.println("Retry count form event : " + e.getNumberOfRetryAttempts()));
    }


    @Retry(name = "restaurant-menu", fallbackMethod = "calculateFallback")
    public String calculate(final Order orderParam) {
        Menu menu = new Menu();
        menu.setPhoneNumber(orderParam.getNumber());
        menu.setMeals(orderParam.getMeals());
        MenuResponse responseLoc = this.rmc.calculateMenu(menu);
        return responseLoc.getMessage() + " fiyat : " + responseLoc.getAmount();

    }

    public String calculate3(final Order orderParam) {
        Application applicationLoc = this.ec.getApplication("RESTAURANT-APIGATEWAY");
        List<InstanceInfo> instancesLoc = applicationLoc.getInstances();
        for (InstanceInfo instanceInfoLoc : instancesLoc) {
            System.out.println(instanceInfoLoc);
        }
        Menu menu = new Menu();
        menu.setPhoneNumber(orderParam.getNumber());
        menu.setMeals(orderParam.getMeals());
        InstanceInfo instanceInfoLoc = instancesLoc.get(0);
        RestTemplate restTemplateLoc = this.restTemplateBuilder.build();
        MenuResponse responseLoc = restTemplateLoc.postForObject("http://"
                                                                 + instanceInfoLoc.getIPAddr()
                                                                 + ":"
                                                                 + instanceInfoLoc.getPort()
                                                                 + "/api/v1/restaurant/menu/calculate",
                                                                 menu,
                                                                 MenuResponse.class);
        return responseLoc.getMessage() + " fiyat : " + responseLoc.getAmount();

    }

    @Retry(name = "restaurant-menu", fallbackMethod = "calculateFallback")
    public String calculate2(final Order orderParam) {
        io.github.resilience4j.retry.Retry retryLoc = this.rr.retry("restaurant-menu");
        Metrics metricsLoc = retryLoc.getMetrics();
        System.out.println("r1 : "
                           + metricsLoc.getNumberOfSuccessfulCallsWithRetryAttempt()
                           + " r2 : "
                           + metricsLoc.getNumberOfSuccessfulCallsWithoutRetryAttempt()
                           + " r3  : "
                           + metricsLoc.getNumberOfFailedCallsWithoutRetryAttempt()
                           + " r4  : "
                           + metricsLoc.getNumberOfFailedCallsWithRetryAttempt());
        Menu menu = new Menu();
        menu.setPhoneNumber(orderParam.getNumber());
        menu.setMeals(orderParam.getMeals());
        MenuResponse responseLoc = this.rt.postForObject("http://RESTAURANT-APIGATEWAY/api/v1/restaurant/menu/calculate",
                                                         menu,
                                                         MenuResponse.class);
        return responseLoc.getMessage() + " fiyat : " + responseLoc.getAmount();
    }

    public String calculateFallback(final Order orderParam,
                                    final Throwable throwableParam) throws Throwable {
        if (throwableParam instanceof HttpFeignException) {
            throw throwableParam;
        }
        System.out.println("calculateFallback");
        return "Fallback : price for menu 100TL";
    }

}
