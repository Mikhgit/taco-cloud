package com.tacos.taco.client.http;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(
        name = "taco-service",
        url = "",
        path = ""
        //todo
)
public class TacoFeignClient {
}
