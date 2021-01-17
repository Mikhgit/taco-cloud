package com.tacos.taco.controller;

import com.tacos.taco.controller.mapper.TacoDtoMapper;
import com.tacos.taco.domain.TacoEntity;
import com.tacos.taco.dto.NewTacoFormDto;
import com.tacos.taco.dto.TacoDto;
import com.tacos.taco.service.TacoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(produces = "application/json")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class DesignTacoController {

    private final TacoService tacoService;
    private final TacoDtoMapper mapper;

    @GetMapping("/recent")
    public List<TacoDto> recentTacos() {
        return tacoService.findRecentTacos().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public TacoEntity createTaco(@RequestBody NewTacoFormDto form) {
        return tacoService.create(mapper.fromDto(form));
    }

    @GetMapping("/{id}")
    public TacoDto tacoById(@PathVariable("id") String id) {
        return mapper.toDto(tacoService.getById(id));
    }
}
