package com.tacos.ingredient.client.http;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(clients = IngredientFeignClient.class)
public class IngredientClientAutoConfiguration {

}
