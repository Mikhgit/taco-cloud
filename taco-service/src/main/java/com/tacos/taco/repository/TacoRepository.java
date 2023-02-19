package com.tacos.taco.repository;

import com.tacos.taco.domain.TacoEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface TacoRepository extends ReactiveCrudRepository<TacoEntity, String> {
}
