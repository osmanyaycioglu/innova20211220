package com.training.spring.order.retry;

import java.io.IOException;
import java.net.ConnectException;
import java.util.function.Predicate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.ResourceAccessException;

import com.training.error.ErrorObj;
import com.training.error.HttpFeignException;

public class RestaurantMenuRetryExceptionHandler implements Predicate<Throwable> {

    private static final Logger logger = LoggerFactory.getLogger(RestaurantMenuRetryExceptionHandler.class);


    @Override
    public boolean test(final Throwable tParam) {
        if (RestaurantMenuRetryExceptionHandler.logger.isInfoEnabled()) {
            RestaurantMenuRetryExceptionHandler.logger.info("[RestaurantMenuRetryExceptionHandler][test]-> "
                                                            + tParam.getClass()
                                                            + " message : "
                                                            + tParam.getMessage());
        }
        if (tParam instanceof IllegalArgumentException) {
            return false;
        }
        if (tParam instanceof HttpFeignException) {
            HttpFeignException exceptionLoc = (HttpFeignException) tParam;
            ErrorObj errorObjLoc = exceptionLoc.getErrorObj();
            // neyse
            return false;
        }
        if (IOException.class.isAssignableFrom(tParam.getClass())) {
            System.out.println("Retry with IOException");
            return true;
        }
        if (tParam instanceof ConnectException) {
            System.out.println("Retry with ConnectException");
            return true;
        }
        if (tParam instanceof ResourceAccessException) {
            System.out.println("Retry with ResourceAccessException");
            return true;
        }
        return false;
    }

}
