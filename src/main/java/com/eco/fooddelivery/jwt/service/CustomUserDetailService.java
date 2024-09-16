package com.eco.fooddelivery.jwt.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.eco.fooddelivery.jwt.model.User;
import com.eco.fooddelivery.jwt.repository.IUserRepository;

@Service
public class CustomUserDetailService implements UserDetailsService
{
    private IUserRepository iUserRepository;

    public CustomUserDetailService( IUserRepository iUserRepository )
    {
        this.iUserRepository = iUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        User user = iUserRepository.findByEmail(username).orElseThrow( () -> new RuntimeException("User not found !!") );

        return user;
    }
}