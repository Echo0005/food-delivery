package com.eco.fooddelivery.service;

import org.springframework.stereotype.Service;

import com.eco.fooddelivery.repository.IUserRepository;

@Service
public class UserService
{
    private IUserRepository iUserRepository;

    public UserService( IUserRepository iUserRepository )
    {
        this.iUserRepository = iUserRepository;
    }
}
