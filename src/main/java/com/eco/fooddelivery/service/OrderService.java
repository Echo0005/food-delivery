package com.eco.fooddelivery.service;

import org.springframework.stereotype.Service;

import com.eco.fooddelivery.repository.IOrderRepository;

@Service
public class OrderService
{
    private IOrderRepository iOrderRepository;

    public OrderService( IOrderRepository iOrderRepository )
    {
        this.iOrderRepository = iOrderRepository;
    }

    
}