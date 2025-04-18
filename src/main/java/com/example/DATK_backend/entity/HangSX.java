package com.example.DATK_backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.hibernate.annotations.Nationalized;

@Entity
public class HangSX {
    @Id
    @Nationalized
    @Column(name = "MaHangSX", nullable = false, length = 10)
    private String maHangSX;

    @Nationalized
    @Column(name = "TenHang", nullable = false, length = 50)
    private String tenHang;

    @Nationalized
    @Column(name = "DiaChi", length = 50)
    private String diaChi;

    @Nationalized
    @Column(name = "SoDT", length = 10)
    private String soDT;

    @Nationalized
    @Column(name = "Email", length = 50)
    private String email;

    public String getMaHangSX() {
        return maHangSX;
    }

    public void setMaHangSX(String maHangSX) {
        this.maHangSX = maHangSX;
    }

    public String getTenHang() {
        return tenHang;
    }

    public void setTenHang(String tenHang) {
        this.tenHang = tenHang;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSoDT() {
        return soDT;
    }

    public void setSoDT(String soDT) {
        this.soDT = soDT;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}