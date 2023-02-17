package com.tacos.order.service;

import com.tacos.order.domain.OrderEntity;

import java.util.List;

public interface OrderService {
    List<OrderEntity> findAll();

    OrderEntity create(NewOrderForm form);

    OrderEntity update(OrderEntity order);

    OrderEntity patch(String orderId, OrderEntity patch);
}
