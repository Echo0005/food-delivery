package com.eco.fooddelivery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eco.fooddelivery.model.Client;

@Repository
public interface IClientRepository extends JpaRepository< Client, Long >
{
    
}