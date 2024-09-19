package com.eco.fooddelivery.model;

import java.util.List;

import com.eco.fooddelivery.jwt.model.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
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
    private List<Product> products;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;

    private String date;

    private boolean isTaking;
    private boolean isDelivery;
}