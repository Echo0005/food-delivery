package com.eco.fooddelivery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eco.fooddelivery.model.Order;

@Repository
public interface IOrderRepository extends JpaRepository< Order, Long >
{
    
}