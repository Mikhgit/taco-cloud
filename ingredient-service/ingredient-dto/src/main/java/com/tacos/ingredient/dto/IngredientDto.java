package com.tacos.ingredient.dto;

import lombok.Value;
import lombok.Builder;

@Value
@Builder
public class IngredientDto {

    String id;
    String name;
    TypeDto type;

    public enum TypeDto {
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
    }
}
