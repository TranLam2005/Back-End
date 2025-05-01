package com.example.DATK_backend.service;
import com.example.DATK_backend.entity.ListOrder;
import com.example.DATK_backend.repository.RepositoryListOrders;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ServiceListOrder {
    private final RepositoryListOrders repositoryListOrders;
    public ServiceListOrder(RepositoryListOrders repositoryListOrders) {
        this.repositoryListOrders = repositoryListOrders;
    }

    public List<ListOrder> getListOrder() {
        return repositoryListOrders.getListOrder();
    }

    public void addOrders (ListOrder listOrder) {
        repositoryListOrders.save(listOrder);
    }
}
