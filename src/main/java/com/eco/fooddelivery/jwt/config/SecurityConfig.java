package com.eco.fooddelivery.jwt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.eco.fooddelivery.jwt.model.Role;
import com.eco.fooddelivery.jwt.security.JwtAuthenticationEntryPoint;
import com.eco.fooddelivery.jwt.security.JwtAuthenticationFilter;

@Configuration
public class SecurityConfig
{
    private JwtAuthenticationEntryPoint point;
    private JwtAuthenticationFilter filter;
    private UserDetailsService userDetailsService;
    private PasswordEncoder passwordEncoder;

    public SecurityConfig
    ( 
        JwtAuthenticationEntryPoint point,
        JwtAuthenticationFilter filter,
        UserDetailsService userDetailsService,
        PasswordEncoder passwordEncoder
    )
    {
        this.point = point;
        this.filter = filter;
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public SecurityFilterChain securityFilterChain( HttpSecurity http ) throws Exception
    {
        http.csrf( csrf -> csrf.disable() )
                .cors( cors -> cors.disable() )
                
                .authorizeHttpRequests
                (
                    auth -> auth
                        .requestMatchers("/auth/login", "/auth/create-user").permitAll()
                        .requestMatchers( "/products/all" ).authenticated()
                        .requestMatchers( "/orders/all-user" ).authenticated()
                        .requestMatchers("/orders/all", "/products/create","/products/edit", "/products/delete").hasAuthority(Role.ADMIN.name())
                        .requestMatchers("/orders/create", "/orders/edit", "/orders/delete").hasAuthority(Role.USER.name())

                        .anyRequest().authenticated()
                )
                
                .exceptionHandling( ex -> ex.authenticationEntryPoint(point) )
                .sessionManagement( session -> session.sessionCreationPolicy( SessionCreationPolicy.STATELESS ) );
        
        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
        
        return http.build();
    }

    @Bean
    public DaoAuthenticationProvider doDaoAuthenticationProvider()
    {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();

        provider.setUserDetailsService( userDetailsService );
        provider.setPasswordEncoder( passwordEncoder );

        return provider;
    }
}