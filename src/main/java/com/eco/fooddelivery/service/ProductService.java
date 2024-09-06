package com.eco.fooddelivery.service;

import org.springframework.stereotype.Service;

import com.eco.fooddelivery.repository.IProductRepository;

@Service
public class ProductService
{
    private IProductRepository iProductRepository;

    public ProductService( IProductRepository iProductRepository )
    {
        this.iProductRepository = iProductRepository;
    }

    
}
