package com.eco.fooddelivery.service;

import org.springframework.stereotype.Service;

import com.eco.fooddelivery.repository.IClientRepository;

@Service
public class ClientService
{
    private IClientRepository iClientRepository;

    public ClientService( IClientRepository iClientRepository )
    {
        this.iClientRepository = iClientRepository;
    }

    
}
