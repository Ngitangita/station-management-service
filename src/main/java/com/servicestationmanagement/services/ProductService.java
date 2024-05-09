package com.servicestationmanagement.services;

import java.util.List;

import com.servicestationmanagement.entities.Product;

public interface ProductService {
    Product updateProduct(Integer id, Product product);

    Product createProduct(Product product);

    Product findProductById(Integer id);

    Product destroyProductById(Integer id);

    List<Product> findAll(); 
}
