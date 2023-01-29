package com.tacos.ingredient.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import com.tacos.ingredient.controller.mapper.IngredientDtoMapper;
import com.tacos.ingredient.dto.IngredientDto;
import com.tacos.ingredient.repository.IngredientRepository;
import com.tacos.ingredient.domain.IngredientEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/ingredients", produces = "application/json")
@CrossOrigin(origins = "*") //позволяет клиентам из любого домена использовать API (необходим для Angular-клиента)
@RequiredArgsConstructor
public class IngredientController {

    private final IngredientRepository repo;
    private final IngredientDtoMapper mapper;

    @GetMapping
    public List<IngredientDto> allIngredients() {
        return repo.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public IngredientDto getIngredientById(@PathVariable String id) {
        return repo.findById(id).map(mapper::toDto).orElse(null);
    }

    @PutMapping("/{id}")
    public void updateIngredient(@PathVariable String id, @RequestBody IngredientDto ingredient) {
        if (!ingredient.getId().equals(id)) {
            throw new IllegalStateException("Given ingredient's ID doesn't match the ID in the path.");
        }
        repo.save(mapper.fromDto(ingredient));
    }

    @PostMapping
    public ResponseEntity<IngredientDto> postIngredient(@RequestBody IngredientDto ingredient) {
        IngredientEntity saved = repo.save(mapper.fromDto(ingredient));
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create("http://localhost:8080/ingredients/" + saved.getId()));
        return new ResponseEntity<>(mapper.toDto(saved), headers, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public void deleteIngredient(@PathVariable String id) {
        repo.deleteById(id);
    }
}
