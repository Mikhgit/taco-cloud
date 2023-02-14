package com.tacos.order.client;

import com.tacos.order.domain.OrderEntity;

public interface OrderAmqpClient {

    void orderCreated(OrderEntity order);
}
