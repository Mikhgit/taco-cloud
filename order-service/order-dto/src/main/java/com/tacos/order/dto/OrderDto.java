package com.tacos.order.dto;

import lombok.Builder;
import lombok.Value;

import java.util.Date;
import java.util.List;

@Value
@Builder
public class OrderDto {

    String id;
    Date placedAt;
    String deliveryName;
    String deliveryStreet;
    String deliveryCity;
    String deliveryState;
    String deliveryZip;
    String ccNumber;
    String ccExpiration;
    String ccCVV;
    List<String> tacos;
}
