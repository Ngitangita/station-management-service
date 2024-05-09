package com.servicestationmanagement.services.impl;
import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.servicestationmanagement.entities.Product;
import com.servicestationmanagement.exception.BadRequestException;
import com.servicestationmanagement.exception.InternalServerException;
import com.servicestationmanagement.exception.NotFoundException;
import com.servicestationmanagement.repositories.ProductRepository;
import com.servicestationmanagement.services.ProductService;


@Service
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Override
    public Product updateProduct(Integer id, Product product) {
        try {
            final var foundProductOptional = productRepository.findById(id);
            if (foundProductOptional.isPresent()){
                var foundProduct = foundProductOptional.get();
                foundProduct.setName(product.getName());
                foundProduct.setQuantity(product.getQuantity());
                foundProduct.setUnitPrice(product.getUnitPrice());
                return productRepository.update(foundProduct);
            }
            throw new NotFoundException("Product not found");
        } catch (SQLException e) {
            throw new InternalServerException(e.getMessage());
        }
    }

    @Override
    public Product createProduct(Product product) {
        try {
            if (product.getId() == null){
                return productRepository.create(product);
            }
            throw new BadRequestException("Product ID must be null for creation");
        } catch (SQLException e) {
            throw new InternalServerException(e.getMessage());
        }
    }

    @Override
    public Product findProductById(Integer id) {
        try {
            return productRepository.findById(id).orElseThrow(() -> new NotFoundException("Product not found"));
        } catch (SQLException e) {
            throw new InternalServerException(e.getMessage());
        }
    }

    @Override
    public Product destroyProductById(Integer id) {
        try {
            return productRepository.delete(id).orElseThrow(() -> new NotFoundException("Product not found"));
        } catch (SQLException e) {
            throw new InternalServerException(e.getMessage());
        }
    }

    @Override
    public List<Product> findAll() {
        try {
            return productRepository.findAll();
        } catch (SQLException e) {
            throw new InternalServerException(e.getMessage());
        }
    }

}
