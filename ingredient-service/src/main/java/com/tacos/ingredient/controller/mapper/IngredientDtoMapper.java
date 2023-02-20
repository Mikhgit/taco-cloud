package com.tacos.ingredient.controller.mapper;

import com.tacos.ingredient.domain.IngredientEntity;
import com.tacos.ingredient.dto.IngredientDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface IngredientDtoMapper {

    IngredientDto toDto(IngredientEntity ingredient);

    IngredientEntity fromDto(IngredientDto ingredient);
}
