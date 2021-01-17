package com.tacos.taco.service;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class NewTacoForm {

    private String name;
    private List<String> ingredients;
}
