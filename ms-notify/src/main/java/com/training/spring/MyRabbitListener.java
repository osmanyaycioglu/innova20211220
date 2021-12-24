package com.training.spring;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

@Component
public class MyRabbitListener {

    @Value("${server.port}")
    private int port;

    @RabbitListener(bindings = @QueueBinding(value = @Queue(name = "sms-queue", durable = "true", autoDelete = "false"),
                                             exchange = @Exchange(name = "notfication-exchange",
                                                                  autoDelete = "false",
                                                                  durable = "true",
                                                                  type = ExchangeTypes.DIRECT),
                                             key = "sms-not"))
    @SendTo("response-exchange/not-response")
    public String handleSMS(final NotifyMessage str) {
        try {
            Thread.sleep(5_000);
        } catch (Exception eLoc) {
        }
        System.out.println(this.port + " Received SMS : " + str);
        return "SMS Message for : " + str.getDestination() + " has been sent.";
    }

    @RabbitListener(bindings = @QueueBinding(value = @Queue(name = "mail-queue",
                                                            durable = "true",
                                                            autoDelete = "false"),
                                             exchange = @Exchange(name = "notfication-exchange",
                                                                  autoDelete = "false",
                                                                  durable = "true",
                                                                  type = ExchangeTypes.DIRECT),
                                             key = "mail-not"))
    @SendTo("response-exchange/not-response")
    public String handleMail(final NotifyMessage str) {
        try {
            Thread.sleep(5_000);
        } catch (Exception eLoc) {
        }
        System.out.println(this.port + " Received MAIL : " + str);
        return "MAIL Message for : " + str.getDestination() + " has been sent.";
    }

    @RabbitListener(bindings = @QueueBinding(value = @Queue(name = "mail-t-queue",
                                                            durable = "true",
                                                            autoDelete = "false"),
                                             exchange = @Exchange(name = "notfication-topic",
                                                                  autoDelete = "false",
                                                                  durable = "true",
                                                                  type = ExchangeTypes.TOPIC),
                                             key = "message.mail.tr.#"))
    public void handleTMail(final NotifyMessage str) {
        System.out.println(this.port + " Received Topic MAIL : " + str);

    }

    @RabbitListener(bindings = @QueueBinding(value = @Queue(name = "sms-t-queue",
                                                            durable = "true",
                                                            autoDelete = "false"),
                                             exchange = @Exchange(name = "notfication-topic",
                                                                  autoDelete = "false",
                                                                  durable = "true",
                                                                  type = ExchangeTypes.TOPIC),
                                             key = "message.sms.tr.#"))
    public void handleTSMS(final NotifyMessage str) {
        System.out.println(this.port + " Received Topic MAIL : " + str);

    }

    @RabbitListener(bindings = @QueueBinding(value = @Queue(name = "all-t-queue",
                                                            durable = "true",
                                                            autoDelete = "false"),
                                             exchange = @Exchange(name = "notfication-topic",
                                                                  autoDelete = "false",
                                                                  durable = "true",
                                                                  type = ExchangeTypes.TOPIC),
                                             key = "message.#"))
    public void handleAllTMail(final NotifyMessage str) {
        System.out.println(this.port + " Received ALL : " + str);

    }

}
