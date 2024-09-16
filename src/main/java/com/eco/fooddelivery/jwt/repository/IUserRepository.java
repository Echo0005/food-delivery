package com.eco.fooddelivery.jwt.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eco.fooddelivery.jwt.model.Role;
import com.eco.fooddelivery.jwt.model.User;

@Repository
public interface IUserRepository extends JpaRepository<User, Long>
{
    public Optional<User> findByEmail( String email );
    public Optional<User> findByRole(Role role);
}