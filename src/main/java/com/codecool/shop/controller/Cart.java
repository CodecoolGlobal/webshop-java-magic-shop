package com.codecool.shop.controller;

import java.util.ArrayList;

public class Cart {


    private static Cart instance = null;

    private Cart(){}

    public static Cart getInstance() {
        if (instance == null) {
            instance = new Cart();
        }
        return instance;
    }


    ArrayList<LineItem> productsInCart = new ArrayList<>();

    float sumOfCart = 0;


    public ArrayList<LineItem> getProductsInCart() {
        return productsInCart;
    }

    public float getSumOfCart() {
        return sumOfCart;
    }

    public void addToCart(LineItem item) {
            productsInCart.add(item);
    }

    public void removeFromCart(LineItem item){
        productsInCart.remove(item);
    }
}
