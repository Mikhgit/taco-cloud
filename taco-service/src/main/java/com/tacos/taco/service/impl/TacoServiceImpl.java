package com.tacos.taco.service.impl;

import com.tacos.taco.domain.TacoEntity;
import com.tacos.taco.repository.TacoRepository;
import com.tacos.taco.service.NewTacoForm;
import com.tacos.taco.service.TacoService;
import lombok.RequiredArgsConstructor;
import org.reactivestreams.Publisher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TacoServiceImpl implements TacoService {

    private final TacoRepository tacoRepository;

    @Override
    public Mono<TacoEntity> getById(String id) {
        return tacoRepository.findById(id);
    }

    @Override
    public Flux<TacoEntity> findRecentTacos() {
        return tacoRepository.findAll()
                .take(12)
                .sort(Comparator.comparing(TacoEntity::getCreatedAt));
    }

    @Override
    public Mono<TacoEntity> create(NewTacoForm form) {
        return Mono.just(form)
                .map(newTaco -> TacoEntity.builder()
                        .name(newTaco.getName())
                        .ingredients(newTaco.getIngredients())
                        .build())
                .flatMap(tacoRepository::save);
    }
}
