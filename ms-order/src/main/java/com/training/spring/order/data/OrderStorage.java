package com.training.spring.order.data;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

import com.training.spring.order.models.Order;

@Service
public class OrderStorage {

    private final Map<Long, Order> orderMap = new ConcurrentHashMap<>(1000,
                                                                      0.9f,
                                                                      100);

    public void add(final Order orderParam) {
        this.orderMap.put(orderParam.getOrderId(),
                          orderParam);
    }
    //    private final AtomicLong       counter  = new AtomicLong();
    //    private Long                   counter2 = 0L;
    //    private final List<Order>      list     = new ArrayList<>();
    //
    //    private final Queue<Order>     orders   = new ArrayBlockingQueue<>(100);
    //
    //    public synchronized void increase() {
    //        this.counter2++;
    //    }

}
