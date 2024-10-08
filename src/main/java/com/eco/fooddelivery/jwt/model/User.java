package com.eco.fooddelivery.jwt.model;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.eco.fooddelivery.model.Order;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table( name = "user_table" )
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails
{
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    private Long userId;
    private String name;
    private String email;

    private String password;

    private Role role;

    @JsonIgnore
    @OneToMany( mappedBy = "user" )
    private Set<Order> orders;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }
    @Override
    public String getUsername()
    {
        return email;
    }
}