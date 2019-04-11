package com.codecool.shop.controller;

import com.codecool.shop.model.Product;

public class LineItem {

    private Product product;
    private int quantity;
    private float addUpPrice;

    public LineItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
        this.addUpPrice = product.getDefaultPrice()*quantity;
    }

    public Product getProduct() {
        return product;
    }

    public float getAddUpPrice() {
        return addUpPrice;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

}

