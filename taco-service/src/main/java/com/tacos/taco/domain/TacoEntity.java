package com.tacos.taco.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Document(collection = "tacos")
public class TacoEntity {

    @Id
    private String id;

    @NotNull
    @Size(min=5, message="Name must be at least 5 characters long")
    private String name;

    private Date createdAt;

    @Size(min=1, message="You must choose at least 1 ingredient")
    private List<String> ingredients = new ArrayList<>();

    @Builder
    public TacoEntity(@NonNull String name,
                      @NonNull List<String> ingredients) {
        this.name = name;
        this.ingredients = ingredients;
        this.createdAt = new Date();
    }
}
