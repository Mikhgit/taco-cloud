package com.tacos.order.gateway.impl;

import com.tacos.order.domain.OrderEntity;
import com.tacos.order.gateway.OrderGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RabbitOrderGateway implements OrderGateway {

    private final RabbitTemplate rabbit;

    @Override
    public void sendOrder(OrderEntity order) {

//        rabbit.convertAndSend("amq.fanout", "", order, message -> {
//            MessageProperties props = message.getMessageProperties();
//            props.setHeader("X_ORDER_SOURCE", "WEB");
//            return message;
//        });
    }
}
