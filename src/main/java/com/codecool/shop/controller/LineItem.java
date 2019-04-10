package com.codecool.shop.controller;

import com.codecool.shop.model.Product;

public class LineItem {

    Product product;
    int quantity;
    float addUpPrice;

    public LineItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
        this.addUpPrice = product.getDefaultPrice()*quantity;
    }

    public Product getProductOfLineItem () {
        return null;
    }
}
