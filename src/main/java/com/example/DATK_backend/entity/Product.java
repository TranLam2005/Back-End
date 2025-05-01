package com.example.DATK_backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import org.hibernate.annotations.Nationalized;

import java.math.BigDecimal;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Nationalized
    @Lob
    @Column(name = "NameProduct", nullable = false)
    private String nameProduct;

    @Lob
    @Column(name = "SourceImage", nullable = false)
    private String sourceImage;

    @Nationalized
    @Lob
    @Column(name = "Describtion", nullable = false)
    private String describtion;

    @Column(name = "Cost", nullable = false)
    private BigDecimal cost;

    @Nationalized
    @Column(name = "Category", nullable = false, length = 50)
    private String category;

    public Product(int id) {
        this.id = id;
    }
    public Product() {}
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

    public String getSourceImage() {
        return sourceImage;
    }

    public void setSourceImage(String sourceImage) {
        this.sourceImage = sourceImage;
    }

    public String getDescribtion() {
        return describtion;
    }

    public void setDescribtion(String describtion) {
        this.describtion = describtion;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

}