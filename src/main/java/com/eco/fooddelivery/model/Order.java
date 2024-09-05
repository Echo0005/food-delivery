package com.eco.fooddelivery.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order
{
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    private Long id;

    @ManyToOne
    @JoinColumn( name = "order_id", nullable = false )
    private Product product;

    @ManyToOne
    @JoinColumn( name = "client_id", nullable = false )
    private Client client;

    private boolean isTaking;
    private boolean isDelivery;
}