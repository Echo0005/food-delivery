package com.eco.fooddelivery.service;

import org.springframework.stereotype.Service;

import com.eco.fooddelivery.dto.OrderDto;
import com.eco.fooddelivery.model.Order;
import com.eco.fooddelivery.repository.IOrderRepository;

@Service
public class OrderService
{
    private IOrderRepository iOrderRepository;

    public OrderService( IOrderRepository iOrderRepository )
    {
        this.iOrderRepository = iOrderRepository;
    }

    public Order createOrder( OrderDto orderDto )
    {
        Order order = new Order();

        order.setDelivery(false);
        order.setProducts( order.getProducts() );
        order.setTaking(false);

        return iOrderRepository.save(order);
    }

    // edit: TODO

    public void deleteOrder( OrderDto orderDto )
    {
        iOrderRepository.deleteById( orderDto.getId() );
    }

    
}