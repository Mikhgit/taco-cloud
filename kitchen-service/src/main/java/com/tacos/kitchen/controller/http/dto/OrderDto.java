package com.tacos.kitchen.controller.http.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class OrderDto {

    private String id;
    private Date placedAt;
    private String deliveryName;
    private String deliveryStreet;
    private String deliveryCity;
    private String deliveryState;
    private String deliveryZip;
    private String ccNumber;
    private String ccExpiration;
    private String ccCVV;
    private List<String> tacos;
}