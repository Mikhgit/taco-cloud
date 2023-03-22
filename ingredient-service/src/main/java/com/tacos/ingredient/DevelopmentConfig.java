package com.tacos.ingredient;

import com.tacos.ingredient.domain.IngredientEntity;
import com.tacos.ingredient.repository.IngredientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.tacos.ingredient.domain.IngredientEntity.Type;

@Configuration
public class DevelopmentConfig {

    @Bean
    public CommandLineRunner dataLoader(IngredientRepository repo) {
        if (repo.count() > 0) {
            return args -> {};
        }
        return args -> {
            repo.save(new IngredientEntity("FLTO", "Flour Tortilla", Type.WRAP));
            repo.save(new IngredientEntity("COTO", "Corn Tortilla", Type.WRAP));
            repo.save(new IngredientEntity("GRBF", "Ground Beef", Type.PROTEIN));
            repo.save(new IngredientEntity("CARN", "Carnitas", Type.PROTEIN));
            repo.save(new IngredientEntity("TMTO", "Diced Tomatoes", Type.VEGGIES));
            repo.save(new IngredientEntity("LETC", "Lettuce", Type.VEGGIES));
            repo.save(new IngredientEntity("CHED", "Cheddar", Type.CHEESE));
            repo.save(new IngredientEntity("JACK", "Monterrey Jack", Type.CHEESE));
            repo.save(new IngredientEntity("SLSA", "Salsa", Type.SAUCE));
            repo.save(new IngredientEntity("SRCR", "Sour Cream", Type.SAUCE));
        };
    }
}
