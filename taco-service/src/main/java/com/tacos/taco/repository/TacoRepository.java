package com.tacos.taco.repository;

import com.tacos.taco.domain.TacoEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TacoRepository extends MongoRepository<TacoEntity, String> {
}
