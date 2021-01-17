package com.tacos.taco.client.http;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(clients = TacoFeignClient.class)
public class TacoClientAutoConfiguration {
}
