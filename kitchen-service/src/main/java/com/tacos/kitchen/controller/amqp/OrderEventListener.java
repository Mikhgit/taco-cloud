package com.tacos.kitchen.controller.amqp;

import com.tacos.kitchen.service.KitchenLog;
import com.tacos.kitchen.controller.http.dto.OrderDto;
import com.tacos.kitchen.controller.http.mapper.OrderDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
@RequiredArgsConstructor
public class OrderEventListener {

    private final OrderDtoMapper orderMapper;
    private final KitchenLog log;

    @Bean
    public Consumer<OrderDto> orderCreatedEventLog(){
        return order -> log.displayOrder(orderMapper.fromDto(order));
    }
}
