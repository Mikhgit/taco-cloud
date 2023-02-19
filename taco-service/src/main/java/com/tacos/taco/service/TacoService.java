package com.tacos.taco.service;

import com.tacos.taco.domain.TacoEntity;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface TacoService {

    Mono<TacoEntity> getById(String id);

    Flux<TacoEntity> findRecentTacos();

    Mono<TacoEntity> create(NewTacoForm form);
}
