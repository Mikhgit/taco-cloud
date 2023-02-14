package com.tacos.ingredient.client.http;

import com.tacos.ingredient.client.http.security.OAuthFeignConfig;
import com.tacos.ingredient.dto.IngredientDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(
        value = "ingredient-service",
        path = "/ingredients",
        url = "${taco.ingredient-service.url}",
        configuration = OAuthFeignConfig.class
)
public interface IngredientFeignClient {

    @GetMapping
    List<IngredientDto> allIngredients();

    @GetMapping("/{id}")
    IngredientDto getIngredientById(@PathVariable String id);

    @PutMapping("/{id}")
    void updateIngredient(@PathVariable String id, @RequestBody IngredientDto ingredient);

    @PostMapping
    ResponseEntity<IngredientDto> postIngredient(@RequestBody IngredientDto ingredient);

    @DeleteMapping("/{id}")
    void deleteIngredient(@PathVariable String id);
}
