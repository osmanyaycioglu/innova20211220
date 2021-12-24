package com.training.spring.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.retry.Retry.Metrics;
import io.github.resilience4j.retry.RetryRegistry;

// @Component
public class MyCliStarter implements CommandLineRunner {

    @Autowired
    private MyTest                 mrt;

    @Autowired
    private RetryRegistry          rr;

    @Autowired
    private CircuitBreakerRegistry cbr;

    @Override
    public void run(final String... argsParam) throws Exception {
        CircuitBreaker circuitBreakerLoc = this.cbr.circuitBreaker("restaurant-menu-cb");
        io.github.resilience4j.circuitbreaker.CircuitBreaker.Metrics metricsLoc = circuitBreakerLoc.getMetrics();
        for (int iLoc = 0; iLoc < 100; iLoc++) {
            try {
                this.mrt.test2();
            } catch (Exception eLoc) {
                System.out.println("------Error------"
                                   + eLoc.getClass()
                                         .getName());
                try {
                    Thread.sleep(400);
                } catch (Exception eLoc2) {
                }
            }
            System.out.println(iLoc
                               + " : "
                               + " state : "
                               + circuitBreakerLoc.getState()
                               + " failure : "
                               + metricsLoc.getFailureRate()
                               + " sc : "
                               + metricsLoc.getNumberOfSuccessfulCalls()
                               + " fc : "
                               + metricsLoc.getNumberOfFailedCalls()
                               + " np : "
                               + metricsLoc.getNumberOfNotPermittedCalls()
                               + "");
        }
    }

    public void run2(final String... argsParam) throws Exception {
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
