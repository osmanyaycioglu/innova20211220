package com.training.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.training.error.ErrorHandlingConfig;

@SpringBootApplication
@Import(ErrorHandlingConfig.class)
public class MsRestaurantApplication {

    public static void main(final String[] args) {
        SpringApplication.run(MsRestaurantApplication.class,
                              args);
    }

}
