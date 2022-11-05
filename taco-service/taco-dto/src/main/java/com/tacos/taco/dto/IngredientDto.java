package com.tacos.taco.dto;

import lombok.Builder;
import lombok.Value;

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
