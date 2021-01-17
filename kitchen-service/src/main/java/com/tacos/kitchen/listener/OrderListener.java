package com.tacos.kitchen.listener;

import com.tacos.kitchen.KitchenUI;
import com.tacos.kitchen.domain.Order;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderListener {

    private KitchenUI ui;

    @Autowired
    public OrderListener(KitchenUI ui) {
        this.ui = ui;
    }

    @RabbitListener(queues = "tacocloud.order.notify")
    public void receiveOrder(Order order) {
        ui.displayOrder(order);
    }

}
