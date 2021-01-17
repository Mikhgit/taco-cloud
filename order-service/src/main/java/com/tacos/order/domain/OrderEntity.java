package com.tacos.order.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Document(collection = "orders")
public class OrderEntity {

    @Id
    private String id;
    private Date placedAt = new Date();

    private String deliveryName;
    private String deliveryStreet;
    private String deliveryCity;
    private String deliveryState;
    private String deliveryZip;
    private String ccNumber;
    private String ccExpiration;
    private String ccCVV;

    //id tacos
    private List<String> tacos = new ArrayList<>();

    public void addDesign(String design) {
        this.tacos.add(design);
    }
}
