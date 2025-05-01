package com.example.DATK_backend.repository;

import com.example.DATK_backend.entity.Cart;
import com.example.DATK_backend.entity.CartId;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;

import java.util.List;
@EnableJpaRepositories
public interface RepositoryCart extends JpaRepository<Cart, CartId> {
    @Query("select c from Cart c where c.userId = :userId")
    List<Cart> getCartByMaUser (@Param("userId") int userId);
    @Modifying
    @Transactional
    @Query(value = "insert into Cart (MaUser, MaSanPham, Quantity) values (:userId, :productId, :quantity)", nativeQuery = true)
    void insertCart(@Param("userId") Integer maUser, @Param("productId") Integer maSanPham, @Param("quantity") Integer quantity);

    @Query("select c from Cart c where c.userId = :userId and c.productId = :productId")
    Cart findCartByUserIdAndProductId(@Param("userId") int userId, @Param("productId") int productId);
//    void deleteCartByMaUserAndMaSanPham (int maUser, int maSanPham);
//    void deleteAllByMaUser (int maUser);
}
