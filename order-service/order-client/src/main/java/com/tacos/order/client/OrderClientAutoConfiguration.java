package com.tacos.order.client;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(clients = OrderFeignClient.class)
public class OrderClientAutoConfiguration {

}
