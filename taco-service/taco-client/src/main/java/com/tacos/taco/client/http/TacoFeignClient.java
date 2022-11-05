package com.tacos.taco.client.http;

import com.tacos.taco.dto.NewTacoFormDto;
import com.tacos.taco.dto.TacoDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("taco-service")
public interface TacoFeignClient {

    @GetMapping("/tacos/recent")
    List<TacoDto> recentTacos();

    @PostMapping(consumes = "application/json")
    TacoDto createTaco(@RequestBody NewTacoFormDto form);

    @GetMapping("/{id}")
    TacoDto tacoById(@PathVariable("id") String id);
}
