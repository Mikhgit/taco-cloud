package com.tacos.order.gateway;

import com.tacos.order.domain.OrderEntity;

public interface OrderGateway {

    void sendOrder(OrderEntity order);
}
