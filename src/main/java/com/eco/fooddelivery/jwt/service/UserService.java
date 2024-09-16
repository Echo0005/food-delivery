package com.eco.fooddelivery.jwt.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.eco.fooddelivery.jwt.model.Role;
import com.eco.fooddelivery.jwt.model.User;
import com.eco.fooddelivery.jwt.repository.IUserRepository;

@Service
public class UserService
{
    private IUserRepository iUserRepository;
    private PasswordEncoder passwordEncoder;

    public UserService( IUserRepository iUserRepository, PasswordEncoder passwordEncoder )
    {
        this.iUserRepository = iUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> getUsers()
    {
        return iUserRepository.findAll();
    }

    public User createUserAdmin( User user )
    {
        user.setPassword( passwordEncoder.encode( user.getPassword() ) );
        user.setRole(Role.ADMIN);

        return iUserRepository.save( user );
    }

    public User createUser( User user )
    {
        user.setPassword( passwordEncoder.encode( user.getPassword() ) );
        user.setRole(Role.USER);
        return iUserRepository.save( user );
    }

    public Optional<User> findByRole(Role role)
    {
        return iUserRepository.findByRole(role);
    }
}