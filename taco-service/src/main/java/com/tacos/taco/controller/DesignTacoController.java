package com.tacos.taco.controller;

import com.tacos.taco.controller.mapper.TacoDtoMapper;
import com.tacos.taco.dto.NewTacoFormDto;
import com.tacos.taco.dto.TacoDto;
import com.tacos.taco.service.TacoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/tacos", produces = "application/json")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class DesignTacoController {

    private final TacoService tacoService;
    private final TacoDtoMapper mapper;

    @GetMapping("/recent")
    public Flux<TacoDto> recentTacos() {
        return tacoService.findRecentTacos()
                .map(mapper::toDto);
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<TacoDto> createTaco(@RequestBody NewTacoFormDto form) {
        return Mono.fromSupplier(() -> mapper.fromDto(form))
                .flatMap(tacoService::create)
                .map(mapper::toDto);
    }

    @GetMapping("/{id}")
    public Mono<TacoDto> tacoById(@PathVariable("id") String id) {
        return tacoService.getById(id)
                .map(mapper::toDto);
    }
}
