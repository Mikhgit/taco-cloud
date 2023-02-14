package com.tacos.order.client;

import com.tacos.order.client.security.OAuthFeignConfig;
import com.tacos.order.dto.NewOrderFormDto;
import com.tacos.order.dto.OrderDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(
        value = "order-service",
        path = "/orders",
        url = "${taco.ingredient-service.url}",
        configuration = OAuthFeignConfig.class
)
public interface OrderFeignClient {

    @GetMapping
    List<OrderDto> findAll();

    @PostMapping
    OrderDto create(@RequestBody NewOrderFormDto form);

    @PutMapping(path = "/{orderId}")
    OrderDto putOrder(@RequestBody OrderDto order);

    @PatchMapping(path = "/{orderId}")
    OrderDto patchOrder(@PathVariable String orderId, @RequestBody  OrderDto patch);
}
