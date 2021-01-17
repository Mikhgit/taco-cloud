package com.tacos.taco.service.impl;

import com.tacos.taco.domain.TacoEntity;
import com.tacos.taco.repository.TacoRepository;
import com.tacos.taco.service.NewTacoForm;
import com.tacos.taco.service.TacoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TacoServiceImpl implements TacoService {

    private final TacoRepository tacoRepository;

    @Override
    public TacoEntity getById(String id) {
        return tacoRepository.findById(id).orElseThrow();
    }

    @Override
    public List<TacoEntity> findRecentTacos() {
        PageRequest page = PageRequest.of(0, 12, Sort.by("createdAt").descending());
        return tacoRepository.findAll(page).getContent();
    }

    @Override
    public TacoEntity create(NewTacoForm form) {
        TacoEntity taco = TacoEntity.builder()
                .name(form.getName())
                .ingredients(form.getIngredients())
                .build();
        return tacoRepository.save(taco);
    }
}
