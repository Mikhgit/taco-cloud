package com.tacos.taco.controller.mapper;

import com.tacos.ingredient.client.http.IngredientFeignClient;
import com.tacos.taco.domain.TacoEntity;
import com.tacos.taco.dto.IngredientDto;
import com.tacos.taco.dto.NewTacoFormDto;
import com.tacos.taco.dto.TacoDto;
import com.tacos.taco.service.NewTacoForm;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public abstract class TacoDtoMapper {

    @Autowired
    private IngredientFeignClient ingredientClient;
    public abstract NewTacoForm fromDto(NewTacoFormDto form);

    @Mapping(target = "ingredients", expression = "java( toIngredients(taco.getIngredients()) )")
    public abstract TacoDto toDto(TacoEntity taco);

    protected abstract IngredientDto toIngredientDto(com.tacos.ingredient.dto.IngredientDto ingredientDto);

    protected List<IngredientDto> toIngredients(List<String> ingredientIds) {
        return ingredientIds.stream()
                .map(id -> toIngredientDto(ingredientClient.getIngredientById(id)))
                .collect(Collectors.toList());
    }
}
