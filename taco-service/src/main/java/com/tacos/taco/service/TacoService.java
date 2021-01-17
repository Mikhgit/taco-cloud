package com.tacos.taco.service;

import com.tacos.taco.domain.TacoEntity;

import java.util.List;

public interface TacoService {

    TacoEntity getById(String id);

    List<TacoEntity> findRecentTacos();

    TacoEntity create(NewTacoForm form);
}
