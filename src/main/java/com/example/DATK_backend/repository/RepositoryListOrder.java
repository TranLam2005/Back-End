package com.example.DATK_backend.repository;

import com.example.DATK_backend.entity.ListOrder;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RepositoryListOrder extends JpaRepository<ListOrder, Integer> {
    @Modifying
    @Transactional
    @Query(value = "insert into ListOrders (maUser, maSanPham, quantity) values (:maUser, :maSanPham, :quantity)", nativeQuery = true)
    void addListOrders (@Param("maUser") Integer maUser, @Param("maSanPham") Integer maSanPham, @Param("quantity") Integer quantity);

    @Query("select l from ListOrder l order by l.maUser.userName")
    List<ListOrder> getListOrders();

    void deleteAll();
}
