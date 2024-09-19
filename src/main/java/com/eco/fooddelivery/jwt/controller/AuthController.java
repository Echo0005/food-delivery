package com.eco.fooddelivery.jwt.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eco.fooddelivery.jwt.model.JwtRequest;
import com.eco.fooddelivery.jwt.model.JwtResponse;
import com.eco.fooddelivery.jwt.model.User;
import com.eco.fooddelivery.jwt.security.JwtHelper;
import com.eco.fooddelivery.jwt.service.CustomUserDetailService;
import com.eco.fooddelivery.jwt.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController
{
    private AuthenticationManager manager;
    private JwtHelper helper;
    private UserService userService;
    private CustomUserDetailService customUserDetailService;

    public AuthController
    (
        AuthenticationManager manager,
        JwtHelper helper,
        UserService userService,
        CustomUserDetailService customUserDetailService
    )
    {
        this.manager = manager;
        this.helper = helper;
        this.userService = userService;
        this.customUserDetailService = customUserDetailService;
    }

    @PostMapping("/create-user")
    public ResponseEntity<?> createUser( @RequestBody User user )
    {
        userService.createUser( user );
        return new ResponseEntity<>( HttpStatus.OK );
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) {

        this.doAuthenticate(request.getEmail(), request.getPassword());


        UserDetails userDetails = customUserDetailService.loadUserByUsername(request.getEmail());
        String token = this.helper.generateToken(userDetails);

        JwtResponse response = JwtResponse.builder()
                .jwtToken(token)
                .username(userDetails.getUsername()).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private void doAuthenticate(String email, String password) {

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
        try {
            manager.authenticate(authentication);


        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(" Invalid Username or Password  !!");
        }

    }

    @ExceptionHandler(BadCredentialsException.class)
    public String exceptionHandler() {
        return "Credentials Invalid !!";
    }    
}