package com.example.DATK_backend.repository;

import com.example.DATK_backend.entity.ListOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RepositoryListOrders extends JpaRepository<ListOrder, Integer> {
    @Query("select o from ListOrder o join o.cart c order by c.userId")
    List<ListOrder> getListOrder();
}
