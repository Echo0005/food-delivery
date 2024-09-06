package com.eco.fooddelivery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eco.fooddelivery.model.Product;

@Repository
public interface IProductRepository extends JpaRepository<Product, Long>
{
    
}