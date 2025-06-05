package com.example.DATK_backend.repository;

import com.example.DATK_backend.entity.DiaChi;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RepositoryDiaChi extends JpaRepository<DiaChi, Integer> {
    @Query("select dc from DiaChi dc where dc.userId = :userId")
    DiaChi findDiaChiByUserId(int userId);

    @Modifying
    @Transactional
    @Query(value = "insert into DiaChi (maUser, diaChi, sdt) values (:userId, :diaChi, :sdt)", nativeQuery=true)
    void addDiaChi(@Param("userId") int userId, @Param("diaChi") String diaChi, @Param("sdt") String sdt);

}
