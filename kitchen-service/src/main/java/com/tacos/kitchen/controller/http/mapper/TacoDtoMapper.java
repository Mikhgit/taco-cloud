package com.tacos.kitchen.controller.http.mapper;

import com.tacos.ingredient.client.http.IngredientFeignClient;
import com.tacos.kitchen.domain.Ingredient;
import com.tacos.kitchen.domain.Taco;
import com.tacos.taco.dto.TacoDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
abstract class TacoDtoMapper {

    @Autowired
    private IngredientFeignClient ingredientClient;

    @Mapping(target = "ingredients", expression = "java( toIngredients(taco.getIngredients()) )")
    public abstract Taco fromDto(TacoDto taco);

    public abstract List<Taco> fromDto(List<TacoDto> tacoDtos);

    protected abstract Ingredient toIngredient(com.tacos.ingredient.dto.IngredientDto ingredientDto);

    protected List<Ingredient> toIngredients(List<String> ingredientIds) {
        return ingredientIds.stream()
                .map(ingredientClient::getIngredientById)
                .map(this::toIngredient)
                .collect(Collectors.toList());
    }
}
