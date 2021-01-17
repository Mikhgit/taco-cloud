package com.tacos.taco.dto;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class NewTacoFormDto {

    private String name;
    private List<String> ingredients;
}
