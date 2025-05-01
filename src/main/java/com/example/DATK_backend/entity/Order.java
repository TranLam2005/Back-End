package com.example.DATK_backend.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.Nationalized;

import java.math.BigDecimal;

@Entity
@Table(name = "Orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false)
    private Integer id;

    @Nationalized
    @Lob
    @Column(name = "NameProduct", nullable = false)
    private String nameProduct;

    @Column(name = "Quantity", nullable = false)
    private Integer quantity;

    @Column(name = "Cost", nullable = false)
    private BigDecimal cost;

    @Column(name = "SourceImage", nullable = false)
    private String sourceImage;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public String getSourceImage() {
        return sourceImage;
    }
    public void setSourceImage(String sourceImage) {
        this.sourceImage = sourceImage;
    }
}