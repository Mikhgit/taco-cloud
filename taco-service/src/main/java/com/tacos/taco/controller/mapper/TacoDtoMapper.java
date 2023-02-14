package com.tacos.taco.controller.mapper;

import com.tacos.taco.domain.TacoEntity;
import com.tacos.taco.dto.NewTacoFormDto;
import com.tacos.taco.dto.TacoDto;
import com.tacos.taco.service.NewTacoForm;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public abstract class TacoDtoMapper {

    public abstract NewTacoForm fromDto(NewTacoFormDto form);

    public abstract TacoDto toDto(TacoEntity taco);
}
