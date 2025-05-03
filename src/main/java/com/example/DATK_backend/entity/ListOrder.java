package com.example.DATK_backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "ListOrders")
public class ListOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MaUser", insertable = false, updatable = false)
    private User maUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MaSanPham", insertable = false, updatable = false)
    private Product maSanPham;

    @Column(name = "Quantity")
    private Integer quantity;

    @Column(name = "MaUser")
    private Integer userId;

    @Column(name = "MaSanPham")
    private Integer productId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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