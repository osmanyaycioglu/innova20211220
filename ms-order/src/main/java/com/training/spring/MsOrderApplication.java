package com.training.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Import;

import com.training.error.ErrorHandlingConfig;

//@SpringBootApplication(scanBasePackages = {
//                                            "com.training.spring",
//                                            "com.training.error"
//})
@SpringBootApplication
//@EnableRetry
@EnableFeignClients
@Import(ErrorHandlingConfig.class)
public class MsOrderApplication {

    public static void main(final String[] args) {
        SpringApplication.run(MsOrderApplication.class,
                              args);
    }

}
