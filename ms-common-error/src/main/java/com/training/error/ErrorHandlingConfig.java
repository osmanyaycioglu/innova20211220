package com.training.error;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ErrorHandlingConfig {

    @Bean
    public MyRestErrorHandler myRestErrorHandler() {
        return new MyRestErrorHandler();
    }


}
