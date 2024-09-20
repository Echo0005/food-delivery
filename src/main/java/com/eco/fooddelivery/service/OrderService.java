package com.eco.fooddelivery.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.eco.fooddelivery.dto.OrderDto;
import com.eco.fooddelivery.dto.UserDto;
import com.eco.fooddelivery.jwt.model.User;
import com.eco.fooddelivery.jwt.service.UserService;
import com.eco.fooddelivery.model.Order;
import com.eco.fooddelivery.model.Product;
import com.eco.fooddelivery.repository.IOrderRepository;

@Service
public class OrderService
{
    private IOrderRepository iOrderRepository;
    private UserService userService;
    private ProductService productService;

    public OrderService
    (
        IOrderRepository iOrderRepository,
        UserService userService,
        ProductService productService
    )
    {
        this.iOrderRepository = iOrderRepository;
        this.userService = userService;
        this.productService = productService;
    }

    public List<OrderDto> findAll()
    {
        List<Order> orders = iOrderRepository.findAll();

        List<OrderDto> orderDto = new ArrayList<>();

        OrderDto orderAux = new OrderDto();

        for ( Order order : orders )
        {
            orderAux.setClientId( order.getUser().getUserId() );
            orderAux.setDelivery( order.isDelivery() );
            orderAux.setId( order.getId() );
            orderAux.setProductList( order.getProducts() );
            orderAux.setTaking( order.isTaking() );
            orderAux.setUserName( order.getUser().getUsername() );
            orderAux.setDate( order.getDate() );

            orderDto.add( orderAux );
        }

        return orderDto;
    }

    public List<OrderDto> findAllWhithUser( UserDto userDto )
    {
        List<Order> orders = iOrderRepository.findByuser_userId( userDto.getId() );

        List<OrderDto> orderDto = new ArrayList<>();

        OrderDto orderAux = new OrderDto();

        for ( Order order : orders )
        {
            orderAux.setClientId( order.getUser().getUserId() );
            orderAux.setDelivery( order.isDelivery() );
            orderAux.setId( order.getId() );
            orderAux.setProductList( order.getProducts() );
            orderAux.setTaking( order.isTaking() );
            orderAux.setUserName( order.getUser().getUsername() );

            orderDto.add( orderAux );
        }

        return orderDto;
    }

    public Order findById( OrderDto orderDto )
    {
        return iOrderRepository.findById( orderDto.getId() ).orElseThrow();
    }

    public Order createOrder( OrderDto orderDto )
    {
        Order order = new Order();
        User userOrder = userService.findById( orderDto.getClientId() );
        List<Product> products = productService.findByIds( orderDto.getProductsIds() );
        
        order.setDate( generateDate() );
        order.setDelivery( false );
        order.setTaking( false );

        order.setUser( userOrder );
        order.setProducts( products );

        return iOrderRepository.save( order );
    }
    
    public Order editOrder( OrderDto orderDto )
    {
        Order order = findById( orderDto );
        User userOrder = userService.findById( orderDto.getClientId() );
        List<Product> products = productService.findByIds( orderDto.getProductsIds() );

        order.setId( orderDto.getId() );
        order.setDate( generateDate() );
        order.setDelivery( orderDto.isDelivery() );
        order.setTaking( orderDto.isTaking() );

        order.setUser( userOrder );
        order.setProducts( products );

        return iOrderRepository.save( order );    
    }

    public Order updateOrderAdmin( OrderDto orderDto )
    {
        Order order = findById( orderDto );

        order.setDelivery( orderDto.isDelivery() );
        order.setTaking( orderDto.isTaking() );

        return iOrderRepository.save( order );    
    }

    public void deleteOrder( OrderDto orderDto )
    {
        iOrderRepository.deleteById( orderDto.getId() );
    }


    private String generateDate()
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd-yy hh:mm a");
        String date = simpleDateFormat.format( new Date() );
        return date;
    }
}