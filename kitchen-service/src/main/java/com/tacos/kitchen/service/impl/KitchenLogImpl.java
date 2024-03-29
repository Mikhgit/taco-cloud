package com.tacos.kitchen.service.impl;

import com.tacos.kitchen.domain.Order;
import com.tacos.kitchen.service.KitchenLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KitchenLogImpl implements KitchenLog {

    public void displayOrder(Order order) {
        // TODO: Beef this up to do more than just log the received taco.
        //       To display it in some sort of UI.
        log.info("RECEIVED ORDER:  " + order);
    }
}
