package com.codecool.shop.controller;

import com.codecool.shop.model.Product;

public class LineItem {

    private float price;
    private Product product;
    private int quantity;
    private float addUpPrice;

    public LineItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
        this.price = product.getDefaultPrice();
    }

    public Product getProduct() {
        return product;
    }

    public float getAddUpPrice() {
        if (this.quantity == 1) {
            return price;
        } else {
            return addUpPrice;
        }
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setAddUpPrice(int quantity) {
        this.addUpPrice = price * quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setAddUpPrice(int quantity) {
        this.addUpPrice = price * quantity;
    }

    public float getAddUpPrice() {
        if (this.quantity == 1) {
            return price;
        } else {
            return addUpPrice;
        }
    }
}
