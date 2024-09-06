package com.eco.fooddelivery.dto;

import java.util.Set;

import com.eco.fooddelivery.model.Order;

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ClientDto
{
    private Long id;

    private String address;
    private String email;
    private String name;
    private String password;
    private String phoneNumber;

    private Set<Order> orders;
}