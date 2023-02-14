package com.tacos.kitchen.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class Taco implements Serializable {

    private String name;

    private Date createdAt;

    private List<Ingredient> ingredients;
}
