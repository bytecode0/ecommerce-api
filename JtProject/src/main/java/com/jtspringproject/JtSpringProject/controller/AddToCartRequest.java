package com.jtspringproject.JtSpringProject.controller;

public class AddToCartRequest {
    private int count;
    private int productId;

    // Getters y Setters

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
}