package com.tacos.kitchen.service.impl;

import com.tacos.kitchen.controller.http.dto.OrderDto;
import com.tacos.kitchen.controller.http.mapper.OrderDtoMapper;
import com.tacos.kitchen.domain.Order;
import com.tacos.kitchen.service.OrderReceiver;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
//todo move to controller.amqp package
public class RabbitOrderReceiver implements OrderReceiver {

    public static final String ORDER_CREATED_QUEUE = "order.created.v1.kitchen-service";

    private final RabbitTemplate rabbit;
    private final OrderDtoMapper orderMapper;

    public Order receiveOrder() {
        OrderDto dto = rabbit.receiveAndConvert(ORDER_CREATED_QUEUE, new ParameterizedTypeReference<>() {});
        return orderMapper.fromDto(dto);
    }
}
