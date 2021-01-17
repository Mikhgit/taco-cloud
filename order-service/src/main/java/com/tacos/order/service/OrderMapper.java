package com.tacos.order.service;

import com.tacos.order.domain.OrderEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface OrderMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "placedAt", ignore = true)
    OrderEntity toOrderEntity(NewOrderForm form);
}
