package com.tacos.ingredient.repository;

import com.tacos.ingredient.domain.IngredientEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IngredientRepository extends MongoRepository<IngredientEntity, String> {
}