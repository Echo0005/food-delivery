package com.eco.fooddelivery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eco.fooddelivery.model.User;

@Repository
public interface IUserRepository extends JpaRepository< User, Long >
{
    
}