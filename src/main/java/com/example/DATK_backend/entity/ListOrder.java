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
    @JoinColumns({
            @JoinColumn(name = "MaUser", referencedColumnName = "MaUser", nullable = false, insertable = false, updatable = false),
            @JoinColumn(name = "MaSanPham", referencedColumnName = "MaSanPham", nullable = false, insertable = false, updatable = false)
    })
    private Cart cart;

    @Column(name = "MaUser", nullable = false)
    private Integer maUser;

    @Column(name = "MaSanPham", nullable = false)
    private Integer maSanPham;

    public ListOrder() {}

    public Integer getMaUser() {
        return maUser;
    }

    public void setMaUser(Integer maUser) {
        this.maUser = maUser;
    }

    public Integer getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(Integer maSanPham) {
        this.maSanPham = maSanPham;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

}