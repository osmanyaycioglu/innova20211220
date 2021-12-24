package com.training.spring.order.integration;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.spring.order.integration.models.NotifyMessage;
import com.training.spring.order.models.Order;

@Service
public class NotifyClient {

    @Autowired
    private RabbitTemplate rt;

    public void notifyWithSMS(final Order orderParam,
                              final String calculateParam) {
        NotifyMessage messageLoc = new NotifyMessage();
        messageLoc.setDestination(orderParam.getNumber());
        messageLoc.setMessage(calculateParam);
        this.rt.convertAndSend("notfication-exchange",
                               "sms-not",
                               messageLoc);
    }

    public void notifyWithMail(final Order orderParam,
                               final String calculateParam) {
        NotifyMessage messageLoc = new NotifyMessage();
        messageLoc.setDestination(orderParam.getNumber());
        messageLoc.setMessage(calculateParam);
        this.rt.convertAndSend("notfication-exchange",
                               "mail-not",
                               messageLoc);
    }

}
