package com.training.spring.order.integration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import io.github.resilience4j.retry.Retry.Metrics;
import io.github.resilience4j.retry.RetryRegistry;

@Component
public class MyCliStarter implements CommandLineRunner {

    @Autowired
    private MyRetryTest   mrt;

    @Autowired
    private RetryRegistry rr;

    @Override
    public void run(final String... argsParam) throws Exception {
        io.github.resilience4j.retry.Retry retryLoc = this.rr.retry("restaurant-menu");
        Metrics metricsLoc = retryLoc.getMetrics();
        for (int iLoc = 0; iLoc < 30; iLoc++) {
            this.mrt.test();
            System.out.println("Call index : "
                               + iLoc
                               + " r1 : "
                               + metricsLoc.getNumberOfSuccessfulCallsWithRetryAttempt()
                               + " r2 : "
                               + metricsLoc.getNumberOfSuccessfulCallsWithoutRetryAttempt()
                               + " r3  : "
                               + metricsLoc.getNumberOfFailedCallsWithoutRetryAttempt()
                               + " r4  : "
                               + metricsLoc.getNumberOfFailedCallsWithRetryAttempt());

        }
    }

}
