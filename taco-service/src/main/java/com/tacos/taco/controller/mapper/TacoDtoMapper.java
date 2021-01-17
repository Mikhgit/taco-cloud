package com.tacos.taco.controller.mapper;

import com.tacos.taco.domain.TacoEntity;
import com.tacos.taco.dto.NewTacoFormDto;
import com.tacos.taco.dto.TacoDto;
import com.tacos.taco.service.NewTacoForm;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface TacoDtoMapper {

    NewTacoForm fromDto(NewTacoFormDto form);

    TacoDto toDto(TacoEntity taco);
}
