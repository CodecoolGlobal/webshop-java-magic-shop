package com.codecool.shop.controller;

import com.codecool.shop.model.Product;

public class LineItem {

    Product product;
    int quantity;
    float price;
    float addUpPrice = quantity*price;

    public LineItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
        this.price = product.getDefaultPrice();
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setAddUpPrice(int quantity) {
        this.addUpPrice = price*quantity;
    }

    public float getAddUpPrice() {
        return addUpPrice;
    }
}
