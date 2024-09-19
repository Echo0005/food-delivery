package com.eco.fooddelivery.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.eco.fooddelivery.dto.ProductDto;
import com.eco.fooddelivery.model.Product;
import com.eco.fooddelivery.repository.IProductRepository;

@Service
public class ProductService
{
    private IProductRepository iProductRepository;

    public ProductService( IProductRepository iProductRepository )
    {
        this.iProductRepository = iProductRepository;
    }

    public List<Product> findAll()
    {
        return iProductRepository.findAll();
    }

    private Product findById( ProductDto productDto )
    {
        return iProductRepository.findById( productDto.getId() ).orElseThrow();
    }

    public List<Product> findByIds( List<Long> ids )
    {
        return iProductRepository.findAllById(ids);
    }

    public Product createProduct( ProductDto productDto )
    {
        Product product = new Product();

        product.setImg( productDto.getImg() );
        product.setName( productDto.getName() );
        product.setPrice( productDto.getPrice() );
        product.setDescription( productDto.getDescription() );

        return iProductRepository.save( product );
    }

    public Product editProduct( ProductDto productDto )
    {
        Product product = findById( productDto );

        product.setId( productDto.getId() );
        product.setImg( productDto.getImg() );
        product.setName( productDto.getName() );
        product.setPrice( productDto.getPrice() );
        product.setDescription( productDto.getDescription() );

        return iProductRepository.save( product );
    }

    public void deleteProduct( ProductDto productDto )
    {
        iProductRepository.deleteById( productDto.getId() );
    }
}