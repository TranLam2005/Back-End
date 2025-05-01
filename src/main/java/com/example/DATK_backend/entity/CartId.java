package com.example.DATK_backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CartId implements Serializable {
    @Serial
    private static final long serialVersionUID = -5449465990146373045L;
    @Column(name = "MaUser", nullable = false)
    private Integer maUser;

    @Column(name = "MaSanPham", nullable = false)
    private Integer maSanPham;

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


    public CartId() {
    }
    public CartId(int maUser, int maSanPham) {
        this.maUser = maUser;
        this.maSanPham = maSanPham;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        CartId entity = (CartId) o;
        return Objects.equals(this.maUser, entity.maUser) &&
                Objects.equals(this.maSanPham, entity.maSanPham);
    }

    @Override
    public int hashCode() {
        return Objects.hash(maUser, maSanPham);
    }

}