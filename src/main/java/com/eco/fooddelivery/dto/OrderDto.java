package com.eco.fooddelivery.dto;

import com.eco.fooddelivery.model.Client;
import com.eco.fooddelivery.model.Product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderDto
{
    private Long id;

    private Product product;

    private Client client;

    private boolean isTaking;
    private boolean isDelivery;
}
