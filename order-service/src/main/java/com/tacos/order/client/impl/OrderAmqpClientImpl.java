package com.tacos.order.client.impl;

import com.tacos.order.client.OrderAmqpClient;
import com.tacos.order.controller.mapper.OrderDtoMapper;
import com.tacos.order.domain.OrderEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class OrderAmqpClientImpl implements OrderAmqpClient {

    private final StreamBridge streamBridge;
    private final OrderDtoMapper orderMapper;

    public void orderCreated(OrderEntity order){
        streamBridge.send("orderCreatedEvent-out-0", orderMapper.toDto(order));
    }
}
