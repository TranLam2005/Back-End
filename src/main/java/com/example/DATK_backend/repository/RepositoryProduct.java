package com.example.DATK_backend.repository;

import com.example.DATK_backend.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import java.util.List;
public interface RepositoryProduct extends JpaRepository<Product, Integer> {
    @Query("SELECT p FROM Product p WHERE LOWER(CAST(p.nameProduct AS string)) LIKE LOWER(CONCAT('%', :nameProduct, '%'))")
    List<Product> findByNameProduct(@Param("nameProduct") String nameProduct);
    @Query("select p from Product p where p.nameProduct = :nameProduct and p.id = :id")
    Product findProductByNameProductWithId(@Param("nameProduct") String nameProduct, @Param("id") int id);
    Product findProductByNameProduct(String nameProduct);
    @SuppressWarnings("NullableProblems")
    Page<Product> findAll(@NonNull Pageable pageable);
    List<Product> findProductByCategory(String category);
}
