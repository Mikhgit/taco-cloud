package com.tacos.taco.dto;

import lombok.Value;
import lombok.Builder;

import java.util.Date;
import java.util.List;

@Value
@Builder
public class TacoDto {

    String id;
    String name;
    Date createdAt;
    List<String> ingredients;
}
