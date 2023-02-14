package com.tacos.taco.client.http;

import com.tacos.taco.client.http.security.OAuthFeignConfig;
import com.tacos.taco.dto.NewTacoFormDto;
import com.tacos.taco.dto.TacoDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(
        value = "taco-service",
        path = "/tacos",
        url = "${taco.taco-service.url}",
        configuration = OAuthFeignConfig.class
)
public interface TacoFeignClient {

    @GetMapping("/recent")
    List<TacoDto> recentTacos();

    @PostMapping(consumes = "application/json")
    TacoDto createTaco(@RequestBody NewTacoFormDto form);

    @GetMapping("/{id}")
    TacoDto tacoById(@PathVariable("id") String id);
}
