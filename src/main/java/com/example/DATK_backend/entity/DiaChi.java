package com.example.DATK_backend.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.Nationalized;

@Entity
public class DiaChi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "MaUser", nullable = false, updatable = false, insertable = false)
    private User maUser;

    @Column(name = "MaUser", nullable = false)
    private Integer userId;

    @Nationalized
    @Lob
    @Column(name = "DiaChi", nullable = false)
    private String diaChi;

    @Column(name = "SDT", nullable = false)
    private String sdt;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

}