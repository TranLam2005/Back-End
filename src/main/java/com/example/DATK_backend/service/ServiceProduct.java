package com.example.DATK_backend.service;

import com.example.DATK_backend.entity.Product;
import com.example.DATK_backend.repository.RepositoryProduct;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ServiceProduct {
    private final RepositoryProduct repositoryProduct;
    public ServiceProduct(RepositoryProduct repositoryProduct) {
        this.repositoryProduct = repositoryProduct;
    }
    public List<Product> getAllProducts() {
        return repositoryProduct.findAll();
    }
    public List<Product> findByNameProduct(String nameProduct) {
        return repositoryProduct.findByNameProductContainingIgnoreCase(nameProduct);
    }
}
