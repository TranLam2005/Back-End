package com.example.DATK_backend.service;

import com.example.DATK_backend.entity.ListOrder;
import com.example.DATK_backend.repository.RepositoryListOrder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceListOrder {
    private final RepositoryListOrder repositoryListOrder;
    public ServiceListOrder(RepositoryListOrder repositoryListOrder) {
        this.repositoryListOrder = repositoryListOrder;
    }
    public void addListOrder (int maUser, int maSanPham, int quantity) {
        repositoryListOrder.addListOrders(maUser, maSanPham, quantity);
    }

    public List<ListOrder> getListOrders() {
        return repositoryListOrder.getListOrders();
    }

    public void deleteAll() {
        repositoryListOrder.deleteAll();
    }

    public void deleteListOrdersByMaUser (int productId) {
        repositoryListOrder.deleteListOrdersByProductId(productId);
    }
}
