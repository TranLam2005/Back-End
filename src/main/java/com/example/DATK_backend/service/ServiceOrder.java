package com.example.DATK_backend.service;

import com.example.DATK_backend.entity.Order;
import com.example.DATK_backend.repository.RepositoryOrder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceOrder {
    private final RepositoryOrder repositoryOrder;
    public ServiceOrder(RepositoryOrder repositoryOrder) {
        this.repositoryOrder = repositoryOrder;
    }
    public void createOrder(Order order) {
        repositoryOrder.save(order);
    }
    public List<Order> getAllOrders() {
        return repositoryOrder.findAll();
    }
    public void deleteOrder() {
        repositoryOrder.deleteAll();
    }
}