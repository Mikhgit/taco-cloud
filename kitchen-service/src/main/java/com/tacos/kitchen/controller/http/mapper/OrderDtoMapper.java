package com.tacos.kitchen.controller.http.mapper;

import com.tacos.kitchen.controller.http.dto.OrderDto;
import com.tacos.kitchen.domain.Order;
import com.tacos.taco.client.http.TacoFeignClient;
import com.tacos.taco.dto.TacoDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR, uses = TacoDtoMapper.class)
public abstract class OrderDtoMapper {

    @Autowired
    private TacoFeignClient tacoClient;

    public abstract Order fromDto(OrderDto orderDto);

    protected List<TacoDto> toTacos(List<String> tacoIds) {
        //todo edit to one time request by pageable
        return tacoIds.stream()
                .map(id -> tacoClient.tacoById(id))
                .collect(Collectors.toList());
    }
}
