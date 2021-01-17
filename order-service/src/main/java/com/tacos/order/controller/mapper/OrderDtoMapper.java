package com.tacos.order.controller.mapper;

import com.tacos.order.domain.OrderEntity;
import com.tacos.order.dto.NewOrderFormDto;
import com.tacos.order.dto.OrderDto;
import com.tacos.order.service.NewOrderForm;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface OrderDtoMapper {

    OrderEntity fromDto(OrderDto order);

    NewOrderForm fromDto(NewOrderFormDto form);

    OrderDto toDto(OrderEntity order);
}
