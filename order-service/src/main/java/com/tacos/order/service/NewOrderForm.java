package com.tacos.order.service;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class NewOrderForm {

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
