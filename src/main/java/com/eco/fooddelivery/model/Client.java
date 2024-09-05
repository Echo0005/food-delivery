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
public class Client
{
    // COMPROBAR que el "client_id" termine siendo util para que funcione con el @Join de Order.
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    @Column( name = "client_id" )
    private Long id;

    private String address;
    private String email;
    private String name;
    private String password;
    private String phoneNumber;

    @OneToMany( mappedBy = "orders" )
    private Set<Order> orders;
}