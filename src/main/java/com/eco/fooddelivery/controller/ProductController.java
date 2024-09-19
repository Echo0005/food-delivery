package com.eco.fooddelivery.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eco.fooddelivery.dto.ProductDto;
import com.eco.fooddelivery.model.Product;
import com.eco.fooddelivery.service.ProductService;

/** Administracion de los productos */
@RestController
@RequestMapping("/products")
public class ProductController
{
    private ProductService productService;

    public ProductController( ProductService productService )
    {
        this.productService = productService;
    }

    @GetMapping("/all")
    public ResponseEntity< List<Product> > getAll()
    {
        List<Product> products = productService.findAll();

        return new ResponseEntity<>( products, HttpStatus.OK );
    }

    @PostMapping("/create")
    public ResponseEntity<?> createProduct( @RequestBody ProductDto productDto )
    {
        productService.createProduct( productDto );

        return new ResponseEntity<>( HttpStatus.OK );
    }

    @PutMapping("/edit")
    public ResponseEntity<?> editProduct( @RequestBody ProductDto productDto )
    {
        productService.editProduct( productDto );

        return new ResponseEntity<>( HttpStatus.OK );
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteProduct( @RequestBody ProductDto productDto )
    {
        productService.deleteProduct( productDto );

        return new ResponseEntity<>( HttpStatus.OK );
    }
}