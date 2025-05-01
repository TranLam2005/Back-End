package com.example.DATK_backend.repository;

import com.example.DATK_backend.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryOrder extends JpaRepository<Order, Integer> {
}
