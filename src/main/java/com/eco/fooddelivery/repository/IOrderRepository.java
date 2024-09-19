package com.eco.fooddelivery.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eco.fooddelivery.model.Order;

@Repository
public interface IOrderRepository extends JpaRepository< Order, Long >
{
    /* Explicacion: JPA relaciona con Java hacia la DB.
     *
     * Con "user_userId", indicamos que busque en el atributo "user" del objeto Order (que es la
     * referencia al User), por el atributo "userId".
     */
    List<Order> findByuser_userId( Long userId );
}