package com.eco.fooddelivery.model;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table( name = "client_order" )
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order
{
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    @Column( name = "order_id" )
    private Long id;

    @ManyToMany
    @JoinTable
    (
        name = "product_order",
        joinColumns = @JoinColumn(name = "order_id"),
        inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private Set<Product> products;

    /*
    @ManyToOne
    @JoinColumn( name = "client_id", nullable = false )
    private Client client;
    */

    private boolean isTaking;
    private boolean isDelivery;
}