package com.eco.fooddelivery.dto;

import java.util.ArrayList;
import java.util.List;

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

    private Long clientId;
    private String userName;
    private String date;

    private ArrayList<Long> productsIds;
    private List<Product> productList;

    private boolean isTaking;
    private boolean isDelivery;
}