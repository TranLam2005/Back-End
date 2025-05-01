package com.example.DATK_backend.entity;

public class CartRequest {
    private int maSanPham;
    private int quantity;
    public int getMaSanPham() {
        return maSanPham;
    }
    public void setMaSanPham(int maSanPham) {
        this.maSanPham = maSanPham;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
