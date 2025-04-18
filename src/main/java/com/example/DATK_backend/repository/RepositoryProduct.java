package com.example.DATK_backend.repository;

import com.example.DATK_backend.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RepositoryProduct extends JpaRepository<Product, String> {
    List<Product> findByNameProductContainingIgnoreCase(String nameProduct);
    Page<Product> findAll(Pageable pageable);
}
