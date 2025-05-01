package com.example.DATK_backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Cart {
    @EmbeddedId
    private CartId id;

    @Column(name = "MaUser", insertable = false, updatable = false)
    private Integer userId;
    @Column(name = "MaSanPham", insertable = false, updatable = false)
    private Integer productId;

    @MapsId("maUser")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "MaUser", nullable = false)
    private User maUser;

    @MapsId("maSanPham")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "MaSanPham", nullable = false)
    private Product maSanPham;

    @Column(name = "Quantity", nullable = false)
    private Integer quantity;

    public Cart() {}

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public void setProductId(Integer productId) {
        this.productId = productId;
    }
    public Integer getUserId() {
        return userId;
    }
    public Integer getProductId() {
        return productId;
    }

    public CartId getId() {
        return id;
    }

    public void setId(CartId id) {
        this.id = id;
    }

    public User getMaUser() {
        return maUser;
    }

    public void setMaUser(User maUser) {
        this.maUser = maUser;
    }

    public Product getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(Product maSanPham) {
        this.maSanPham = maSanPham;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

}