package com.eco.fooddelivery.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eco.fooddelivery.dto.Message;
import com.eco.fooddelivery.dto.OrderDto;
import com.eco.fooddelivery.model.Order;
import com.eco.fooddelivery.service.OrderService;

/** Administracion de las Ordenes */
@RestController
@RequestMapping("/orders")
public class OrderController
{
    private OrderService orderService;

    public OrderController( OrderService orderService )
    {
        this.orderService = orderService;
    }

    @GetMapping("/all")
    public ResponseEntity< List<OrderDto> > getAllOrders()
    {
        List<OrderDto> orders = orderService.findAll();
        return new ResponseEntity<>( orders, HttpStatus.FOUND );
    }

    @PostMapping("/create")
    public ResponseEntity<?> createOrder( @RequestBody OrderDto orderDto )
    {
        orderService.createOrder( orderDto );

        return new ResponseEntity<>( HttpStatus.OK );
    }

    @PutMapping("/edit")
    public ResponseEntity<?> editOrder( @RequestBody OrderDto orderDto )
    {
        Order orderStatus = orderService.findById( orderDto );
        if ( orderStatus.isTaking() )
        {
            if ( orderStatus.isDelivery() )
            {
                return new ResponseEntity<>( new Message("Order in delivery, can't edit"), HttpStatus.NOT_ACCEPTABLE );
            }

            return new ResponseEntity<>( new Message("Order taking, can't edit"), HttpStatus.NOT_ACCEPTABLE );
        }

        orderService.editOrder(orderDto);

        return new ResponseEntity<>( new Message("Order edited"), HttpStatus.OK  );
    }

    @PutMapping("/update-admin")
    public ResponseEntity<?> updateOrderAdmin( @RequestBody OrderDto orderDto )
    {
        orderService.updateOrderAdmin( orderDto );

        return new ResponseEntity<>( new Message("Order edited"), HttpStatus.OK  );
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteOrder( @RequestBody OrderDto orderDto )
    {
        Order orderStatus = orderService.findById( orderDto );
        if ( orderStatus.isTaking() )
        {
            if ( orderStatus.isDelivery() )
            {
                return new ResponseEntity<>( new Message("Order in delivery"), HttpStatus.OK );
            }

            return new ResponseEntity<>( new Message("Order taking"), HttpStatus.OK );
        }

        orderService.deleteOrder( orderDto );

        return new ResponseEntity<>( new Message("Order remove"), HttpStatus.OK  );
    }
}