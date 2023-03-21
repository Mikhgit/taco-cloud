package com.tacos.order.service.impl;

import com.tacos.order.client.OrderAmqpClient;
import com.tacos.order.domain.OrderEntity;
import com.tacos.order.repository.OrderRepository;
import com.tacos.order.service.NewOrderForm;
import com.tacos.order.service.OrderMapper;
import com.tacos.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderAmqpClient orderAmqpClient;
    private final OrderRepository repo;
    private final OrderMapper orderMapper;

    @Override
    public List<OrderEntity> findAll() {
        return repo.findAll();
    }

    @Override
    public OrderEntity create(NewOrderForm form) {
        OrderEntity orderEntity = repo.save(orderMapper.toOrderEntity(form));
        orderAmqpClient.orderCreated(orderEntity);
        return orderEntity;
    }

    @Override
    public OrderEntity update(OrderEntity order) {
        return repo.save(order);
    }

    //todo create a utility method to partially update objects
    @Override
    public OrderEntity patch(String orderId, OrderEntity patch) {
        return repo.findById(orderId)
                .map(order -> {
                    if (patch.getDeliveryName() != null) {
                        order.setDeliveryName(patch.getDeliveryName());
                    }
                    if (patch.getDeliveryStreet() != null) {
                        order.setDeliveryStreet(patch.getDeliveryStreet());
                    }
                    if (patch.getDeliveryCity() != null) {
                        order.setDeliveryCity(patch.getDeliveryCity());
                    }
                    if (patch.getDeliveryState() != null) {
                        order.setDeliveryState(patch.getDeliveryState());
                    }
                    if (patch.getDeliveryZip() != null) {
                        order.setDeliveryZip(patch.getDeliveryState());
                    }
                    if (patch.getCcNumber() != null) {
                        order.setCcNumber(patch.getCcNumber());
                    }
                    if (patch.getCcExpiration() != null) {
                        order.setCcExpiration(patch.getCcExpiration());
                    }
                    if (patch.getCcCVV() != null) {
                        order.setCcCVV(patch.getCcCVV());
                    }
                    return order;
                })
                .map(repo::save)
                .orElseThrow(() -> new IllegalArgumentException(orderId));
    }
}