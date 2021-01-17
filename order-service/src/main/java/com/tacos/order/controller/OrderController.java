package com.tacos.order.controller;

import com.tacos.order.controller.mapper.OrderDtoMapper;
import com.tacos.order.dto.NewOrderFormDto;
import com.tacos.order.dto.OrderDto;
import com.tacos.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class OrderController {

    private final OrderDtoMapper mapper;
    private final OrderService orderService;

    @GetMapping
    public List<OrderDto> findAll() {
        return orderService.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDto create(@RequestBody NewOrderFormDto form) {
        return mapper.toDto(orderService.create(mapper.fromDto(form)));
    }

    @PutMapping(path = "/{orderId}")
    public OrderDto putOrder(@RequestBody OrderDto order) {
        return mapper.toDto(orderService.update(mapper.fromDto(order)));
    }

    @PatchMapping(path = "/{orderId}")
    public OrderDto patchOrder(@PathVariable String orderId, @RequestBody  OrderDto patch) {
        return mapper.toDto(orderService.patch(orderId, mapper.fromDto(patch)));
    }
}
