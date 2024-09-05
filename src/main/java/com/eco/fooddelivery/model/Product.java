package com.eco.fooddelivery.model;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product
{
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    @Column( name = "product_id" )
    private Long id;
    private String name;
    private String img;

    @OneToMany(mappedBy = "orders")
    private Set<Order> orders;
}