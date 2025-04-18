package com.example.DATK_backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import org.hibernate.annotations.Nationalized;

@Entity
public class Product {
    @Id
    @Nationalized
    @Column(name = "Id", nullable = false, length = 10)
    private String id;

    @Nationalized
    @Column(name = "NameProduct", nullable = false, length = 50)
    private String nameProduct;

    @Lob
    @Column(name = "SourceImage", nullable = false)
    private String sourceImage;

    @Lob
    @Column(name = "ProductIntroduction", nullable = false)
    private String productIntroduction;

    @Column(name = "Cost")
    private Integer cost;

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getProductIntroduction() {
        return productIntroduction;
    }

    public void setProductIntroduction(String productIntroduction) {
        this.productIntroduction = productIntroduction;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

}