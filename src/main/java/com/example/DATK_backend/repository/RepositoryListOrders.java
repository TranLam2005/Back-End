package com.example.DATK_backend.repository;

import com.example.DATK_backend.entity.ListOrder;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RepositoryListOrders extends JpaRepository<ListOrder, Integer> {
    @Query("select o from ListOrder o")
    List<ListOrder> getListOrder();
    @Modifying
    @Transactional
    @Query(value = "insert into ListOrders (MaUser, MaSanPham) values (:maUser, :maSanPham)", nativeQuery = true)
    void insertListOrder(@Param("maUser") Integer maUser, @Param("maSanPham") Integer maSanPham);
}
